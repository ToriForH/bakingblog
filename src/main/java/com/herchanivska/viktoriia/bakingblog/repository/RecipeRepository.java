package com.herchanivska.viktoriia.bakingblog.repository;

import com.herchanivska.viktoriia.bakingblog.model.Ingredient;
import com.herchanivska.viktoriia.bakingblog.model.Recipe;
import com.herchanivska.viktoriia.bakingblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByName(String name);
    List<Recipe> findByAuthor(User author);

    @Query(value = """
    SELECT DISTINCT * FROM Recipe r
    JOIN recipe_ingredients ri ON r.id = ri.recipe_id
    JOIN Ingredient i ON ri.ingredient_id = i.id
    WHERE i.id = :ingredientId
    """, nativeQuery = true)
    List<Recipe> findByIngredientId(Long ingredientId);

}
