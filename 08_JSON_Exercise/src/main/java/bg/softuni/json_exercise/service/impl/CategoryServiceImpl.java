package bg.softuni.json_exercise.service.impl;

import bg.softuni.json_exercise.data.entities.Category;
import bg.softuni.json_exercise.data.entities.Product;
import bg.softuni.json_exercise.data.repositories.CategoryRepository;
import bg.softuni.json_exercise.service.CategoryService;
import bg.softuni.json_exercise.service.dtos.export.CategoryByProductsDTO;
import bg.softuni.json_exercise.service.dtos.imports.CategorySeedDTO;
import bg.softuni.json_exercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
   //CONSTANTS
    private static final String FILE_PATH="src/main/resources/json/categories.json";

    //BEANS
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {
       if(this.categoryRepository.count()==0) {
           String jsonContent = new String(Files.readAllBytes(Path.of(FILE_PATH)));
//          this.gson.fromJson(new FileReader(FILE_PATH),CategorySeedDTO[].class);

           CategorySeedDTO[] categorySeedDTOS = this.gson.fromJson(jsonContent, CategorySeedDTO[].class);

           for (CategorySeedDTO categorySeedDTO : categorySeedDTOS) {
              if(!this.validationUtil.isValid(categorySeedDTO)){
                   this.validationUtil.getViolations(categorySeedDTO)
                           .forEach(v-> System.out.println(v.getMessage()));
                   continue;
               }
               Category category = this.modelMapper.map(categorySeedDTO, Category.class);
               this.categoryRepository.saveAndFlush(category);
           }
       
       
       }


    }

    @Override
    public List<CategoryByProductsDTO> getAllCategoriesByProducts() {
       return this.categoryRepository.findAllCategoriesByProducts()
                .stream()
                .map(c-> {
                    CategoryByProductsDTO dto = this.modelMapper.map(c, CategoryByProductsDTO.class);
                    dto.setProductsCount(c.getProducts().size());
                    BigDecimal sum = c.getProducts().stream()
                            .map(Product::getPrice)
                            .reduce(BigDecimal::add).get();
                    dto.setTotalRevenue(sum);
                    dto.setAveragePrice(sum.divide(BigDecimal.valueOf(c.getProducts().size()), MathContext.DECIMAL32));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void printAllCategoriesByProducts() {
        String json = this.gson.toJson(this.getAllCategoriesByProducts());
        System.out.println(json);
    }
}
