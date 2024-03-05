package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository <Shampoo, Long > {
 Set<Shampoo> findAllBySizeOrderById(Size size);

 Set<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, long id);

 Set<Shampoo> findAllByPriceGreaterThan(BigDecimal price);
// @Query("SELECT s FROM Shampoo s JOIN SIZE(s.ingredients)>5")

// @Query("SELECT s FROM Shampoo s JOIN s.ingredients i WHERE i.name =(:names)")

 Set<Shampoo> findAllByIngredientsNameIn(@Param(("names")) List<String> name);

 Set <Shampoo> findAllByPriceLessThan(BigDecimal price);

 @Query("FROM Shampoo s WHERE s.ingredients.size < :number ")
 Set <Shampoo> findAllWithIngredientsCountLesserThan(int number);
}
