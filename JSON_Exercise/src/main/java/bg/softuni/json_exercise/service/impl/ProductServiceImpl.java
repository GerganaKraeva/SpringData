package bg.softuni.json_exercise.service.impl;

import bg.softuni.json_exercise.data.entities.Category;
import bg.softuni.json_exercise.data.entities.Product;
import bg.softuni.json_exercise.data.entities.User;
import bg.softuni.json_exercise.data.repositories.CategoryRepository;
import bg.softuni.json_exercise.data.repositories.ProductRepository;
import bg.softuni.json_exercise.data.repositories.UserRepository;
import bg.softuni.json_exercise.service.ProductService;
import bg.softuni.json_exercise.service.dtos.export.ProductInRangeDto;
import bg.softuni.json_exercise.service.dtos.imports.ProductSeedDTO;
import bg.softuni.json_exercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
   private static final String FILE_PATH="src/main/resources/json/products.json";

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private  final CategoryRepository categoryRepository;
    private  final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void seedProducts() throws FileNotFoundException {
            if(this.productRepository.count() == 0) {
                ProductSeedDTO[] productSeedDTOS = this.gson.fromJson(new FileReader(FILE_PATH), ProductSeedDTO[].class);
                for (ProductSeedDTO productSeedDTO : productSeedDTOS) {
                    if (!this.validationUtil.isValid(productSeedDTO)) {
                        this.validationUtil.getViolations(productSeedDTO)
                                .forEach(p -> System.out.println(p.getMessage()));

                        continue;
                    }
                    Product product = this.modelMapper.map(productSeedDTO, Product.class);
                    product.setBuyer(getRandomUser(true));
                    product.setSeller(getRandomUser(false));
                    product.setCategories(getRandomCategories());
                    this.productRepository.saveAndFlush(product);

                }
            }
    }

    @Override
    public List<ProductInRangeDto> getAllProductsInRange(BigDecimal from, BigDecimal to) {
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(from, to)
                .stream()
                .map(p-> {
                    ProductInRangeDto dto = this.modelMapper.map(p, ProductInRangeDto.class);
                     dto.setSeller(p.getSeller().getFirstName() + " "+ p.getSeller().getLastName() );
                     return dto;
                })
                .sorted(Comparator.comparing(ProductInRangeDto::getPrice))
        .collect(Collectors.toList());
    }

    @Override
    public void printAllProductsInRange(BigDecimal from, BigDecimal to) {
         System.out.println(this.gson.toJson(getAllProductsInRange(from, to)));
    }

    private Set<Category> getRandomCategories() {
        Set <Category> categories =new HashSet<>();
        int randomCount = ThreadLocalRandom.current().nextInt(1,4);
        for (int i = 0; i < randomCount; i++) {
            long randomId=ThreadLocalRandom.current().nextLong(1,this.categoryRepository.count()+1);
           categories.add(this.categoryRepository.findById(randomId).get());

        }
        return categories;
    }

    private User getRandomUser(boolean isBuyer) {
       long randomId= ThreadLocalRandom.current().nextLong(1, this.userRepository.count()+1);
        return isBuyer &&  randomId%4 ==0 ? null : this.userRepository.findById(randomId).get();
    }
}
