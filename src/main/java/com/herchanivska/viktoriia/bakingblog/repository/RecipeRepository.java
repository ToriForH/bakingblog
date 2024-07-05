package com.herchanivska.viktoriia.bakingblog.repository;

import com.herchanivska.viktoriia.bakingblog.model.Recipe;
import com.herchanivska.viktoriia.bakingblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = """
    SELECT r.* FROM recipes r
    WHERE r.name LIKE %:name%
    """, nativeQuery = true)
    Set<Recipe> findByName(String name);

    Set<Recipe> findByAuthor(User author);

    @Query(value = """
    SELECT DISTINCT r.* FROM recipes r
    JOIN recipe_ingredients ri ON r.id = ri.recipe_id
    JOIN ingredients i ON ri.ingredient_id = i.id
    WHERE i.id = :ingredientId
    """, nativeQuery = true)
    Set<Recipe> findByIngredientId(Long ingredientId);

    @Query(value = """
            SELECT r.* FROM recipes r
            JOIN liked_recipes lr ON r.id = lr.recipe_id
            WHERE lr.user_id = :userId
            """, nativeQuery = true)
    Set<Recipe> findLikedRecipes(Long userId);

    @Query(value = """
            SELECT r.* FROM recipes r
            JOIN saved_recipes sr ON r.id = sr.recipe_id
            WHERE sr.user_id = :userId
            """, nativeQuery = true)
    Set<Recipe> findSavedRecipes(Long userId);
}
