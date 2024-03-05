package com.example.advquerying.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {

    List<String> getAllShampoosByGivenSize(String size);

    List<String> getAllShampooByGivenSizeOrLabel(String size, long id);

    List<String> getAllShampoosContainingIngredient(List<String> string);

    List<String> getAllShampooWithPriceGraterThan(BigDecimal price);

    int countShampooWithPriceLesserThan(BigDecimal price);

    Set<String> getShampooWithIngredientsLessThan(int numberIngredients);
}
