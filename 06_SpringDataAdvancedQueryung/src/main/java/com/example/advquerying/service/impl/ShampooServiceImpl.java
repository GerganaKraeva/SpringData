package com.example.advquerying.service.impl;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.service.ShampooService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<String> getAllShampoosByGivenSize(String size) {
        Size sizeEnum = Size.valueOf(size.toUpperCase());
        Set<Shampoo> allBySizeOrderById = this.shampooRepository.findAllBySizeOrderById(sizeEnum);
        return allBySizeOrderById
                .stream()
                .map(s -> String.format("%s %s %.2flv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice().doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampooByGivenSizeOrLabel(String size, long id) {
        Size sizeEnum = Size.valueOf(size.toUpperCase());
        Set<Shampoo> allBySizeOrLabelOrderById = this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(sizeEnum,id);
        return allBySizeOrLabelOrderById
                .stream()
                .map(s -> String.format("%s %s %.2flv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice().doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampoosContainingIngredient(List<String> strings) {
        Set<Shampoo> allByIngredientsNameIn = this.shampooRepository.findAllByIngredientsNameIn(strings);
        return allByIngredientsNameIn
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllShampooWithPriceGraterThan(BigDecimal price) {
        Set<Shampoo> allByPriceHigher = this.shampooRepository.findAllByPriceGreaterThan(price);
        return allByPriceHigher
                .stream()
                .map(s -> String.format("%s %s %.2flv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice().doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public int countShampooWithPriceLesserThan(BigDecimal price) {
        return  this.shampooRepository.findAllByPriceLessThan(price).size();
    }

    @Override
    public Set<String> getShampooWithIngredientsLessThan(int numberIngredients) {
        return this.shampooRepository.findAllWithIngredientsCountLesserThan(numberIngredients)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toSet());
    }


}
