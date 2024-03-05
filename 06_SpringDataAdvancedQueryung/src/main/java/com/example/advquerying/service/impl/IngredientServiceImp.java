package com.example.advquerying.service.impl;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.service.IngredientService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImp implements IngredientService {


   private final IngredientRepository ingredientRepository;

    public IngredientServiceImp(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> getAllIngredientThatStartsWith(String symbol) {
        Set<Ingredient> allIngredient = this.ingredientRepository.findAllByNameStartingWith(symbol);
        return allIngredient
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllIngredients(List<String> names) {
        Set<Ingredient> allByIngredientsNameIn = this.ingredientRepository.findAllByNameIn(names);
        return allByIngredientsNameIn
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public int deletedIngredientsByName(String name) {
        return this.ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    public int updateIngredientsPrices() {
             return this.ingredientRepository.updateAllByPrice(BigDecimal.valueOf(1.1));
    }

    @Override
    public int updateIngredientsPricesForGivenNames() {
        return this.ingredientRepository
                .updateAllByPriceForGivenNames(
                        BigDecimal.valueOf(1.1),List.of("Apple","Macadamia Oil"));

    }


}
