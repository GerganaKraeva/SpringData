package bg.softuni.json_exercise.service;

import bg.softuni.json_exercise.service.dtos.export.ProductInRangeDto;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts() throws FileNotFoundException;

    List<ProductInRangeDto> getAllProductsInRange(BigDecimal from, BigDecimal to);
    void printAllProductsInRange(BigDecimal from, BigDecimal to);
}
