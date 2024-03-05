package com.example.advquerying;

import com.example.advquerying.example.ConstructionWorkerRepository;
import com.example.advquerying.example.Developer;
import com.example.advquerying.example.DeveloperRepository;
import com.example.advquerying.service.IngredientService;
import com.example.advquerying.service.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final ShampooService shampooService;

    private final IngredientService ingredientService;
//    private final DeveloperRepository developerRepository;

//    private final ConstructionWorkerRepository constructionWorkerRepository;

    public CommandLineRunnerImpl(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
//        this.developerRepository = developerRepository;
//        this.constructionWorkerRepository = constructionWorkerRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        this.shampooService.getAllShampoosByGivenSize(reader.readLine())
//                .forEach(System.out::println);

//        this.shampooService.getAllShampoosContainingIngredient(List.of(reader.readLine().split(" ")))
//                .forEach(System.out::println);

//        this.developerRepository.findByEmail("Test");
//        this.constructionWorkerRepository.findByEmail("Test");

//        this.shampooService.getAllShampooByGivenSizeOrLabel("MEDIUM", 10)
//                .forEach(System.out::println);

//        this.shampooService.getAllShampooWithPriceGraterThan(BigDecimal.valueOf(5))
//                .forEach(System.out::println);


//        this.ingredientService.getAllIngredientThatStartsWith("M")
//                .forEach(System.out::println);

//        this.ingredientService.getAllIngrediants(List.of(reader.readLine().split(" ")))
//                .forEach(System.out::println);


//        System.out.println(this.shampooService.countShampooWithPriceLesserThan(BigDecimal.valueOf(8.50)));

//        this.shampooService.getShampooWithIngredientsLessThan(2)
//                .forEach(System.out::println);


//        System.out.println(this.ingredientService.deletedIngredientsByName("Nettle"));

//        System.out.println(this.ingredientService.updateIngredientsPrices());

        System.out.println(this.ingredientService.updateIngredientsPricesForGivenNames());

    }
}
