package bg.softuni.json_exercise.service;

import bg.softuni.json_exercise.service.dtos.export.CategoryByProductsDTO;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void seedCategories() throws IOException;
    List<CategoryByProductsDTO> getAllCategoriesByProducts();

    void printAllCategoriesByProducts();
}
