package bg.softuni.json_exercise.data.repositories;

import bg.softuni.json_exercise.data.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository < Category, Long> {

    @Query(value = "FROM Category c ORDER BY SIZE(c.products) DESC")
    Set<Category> findAllCategoriesByProducts();


}
