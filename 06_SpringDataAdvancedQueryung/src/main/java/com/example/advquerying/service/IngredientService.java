package com.example.advquerying.service;

import com.example.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {

    List<String> getAllIngredientThatStartsWith(String symbol);

    List<String> getAllIngredients(List<String> names);

    int deletedIngredientsByName(String name);

    int updateIngredientsPrices();

    int updateIngredientsPricesForGivenNames();

}