package bg.softuni.json_exercise.service.impl;

import bg.softuni.json_exercise.data.entities.User;
import bg.softuni.json_exercise.data.repositories.UserRepository;
import bg.softuni.json_exercise.service.UserService;
import bg.softuni.json_exercise.service.dtos.export.*;
import bg.softuni.json_exercise.service.dtos.imports.UserSeedDTO;
import bg.softuni.json_exercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final String FILE_PATH="src/main/resources/json/users.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, UserRepository userRepository) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public void seedUsers() throws FileNotFoundException {
        if(this.userRepository.count() == 0) {
            UserSeedDTO[] userSeedDTOS = this.gson.fromJson(new FileReader(FILE_PATH), UserSeedDTO[].class);
            for (UserSeedDTO userSeedDTO : userSeedDTOS) {
                if (!this.validationUtil.isValid(userSeedDTO)) {
                    this.validationUtil.getViolations(userSeedDTO)
                            .forEach(v -> System.out.println(v.getMessage()));

                    continue;
                }
                this.userRepository.saveAndFlush(
                        this.modelMapper.map(userSeedDTO, User.class));

            }

        }
    }

    @Override
    public List<UserSoldProductsDTO> getAllUsersAndSoldItems() {
       return this.userRepository.findAll()
               .stream()
               .filter(u ->
                       u.getSold().stream().anyMatch(p -> p.getBuyer() != null))
               .map(u->{
                   UserSoldProductsDTO userDto = this.modelMapper.map(u, UserSoldProductsDTO.class);
                   List<ProductSoldDTO> soldProductsDto = u.getSold()
                           .stream()
                           .filter(p -> p.getBuyer() != null)
                           .map(p -> this.modelMapper.map(p, ProductSoldDTO.class))
                           .collect(Collectors.toList());
                   userDto.setSoldProducts(soldProductsDto);
                   return userDto;
               })
               .sorted(Comparator.comparing(UserSoldProductsDTO::getLastName).thenComparing(UserSoldProductsDTO::getLastName))
               .toList();
    }

    @Override
    public void printAllUsersAndSoldItems() {
        String json = this.gson.toJson(this.getAllUsersAndSoldItems());
        System.out.println(json);
    }

    @Override
    public UserAndProductsDTO getUserAndProductsDto() {
        UserAndProductsDTO userAndProductsDTO=new UserAndProductsDTO();
        List<UserSoldDTO> userSoldDtos = this.userRepository.findAll()
                .stream()
                .filter(u -> !u.getSold().isEmpty())
                .map(u -> {
                    UserSoldDTO userSoldDto = this.modelMapper.map(u, UserSoldDTO.class);
                    ProductSoldByUserDTO productSoldByUserDTO=new ProductSoldByUserDTO();

                    List<ProductInfoDTO> productInfoDtos = u.getSold()
                            .stream()
                            .map(p -> this.modelMapper.map(p, ProductInfoDTO.class))
                            .collect(Collectors.toList());
                    productSoldByUserDTO.setProducts(productInfoDtos);
                    productSoldByUserDTO.setCount(productInfoDtos.size());


                    userSoldDto.setSoldProducts(productSoldByUserDTO);
                    return userSoldDto;
                })
                .sorted((a,b)-> {
                    int countA=a.getSoldProducts().getCount();
                    int countB=b.getSoldProducts().getCount();
                  return Integer.compare(countB,countA);
                })
                .collect(Collectors.toList());

        userAndProductsDTO.setUsers(userSoldDtos);
        userAndProductsDTO.setUsersCount(userSoldDtos.size());
        return userAndProductsDTO;
    }

    @Override
    public void printGetUserAndProductsDTO() {
        String json = this.gson.toJson(this.getUserAndProductsDto());
        System.out.println(json);
    }
}
