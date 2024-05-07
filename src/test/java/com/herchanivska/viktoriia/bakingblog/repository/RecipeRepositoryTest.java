package com.herchanivska.viktoriia.bakingblog.repository;

import com.herchanivska.viktoriia.bakingblog.model.Ingredient;
import com.herchanivska.viktoriia.bakingblog.model.IngredientAmount;
import com.herchanivska.viktoriia.bakingblog.model.Recipe;
import com.herchanivska.viktoriia.bakingblog.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class RecipeRepositoryTest {
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findByIngredientName() {
        String searchName = "cake";
        Set<Recipe> actual = recipeRepository.findByName(searchName);
        for (Recipe recipe: actual) {
            assertTrue(recipe.getName().contains(searchName));
        }
    }

    @Test
    void findByIngredientId() {
        Set<Recipe> actual = recipeRepository.findByIngredientId(7L);
        for (Recipe recipe: actual) {
            Map<Ingredient, IngredientAmount> ingredientAmountMap = recipe.getIngredients();
            Set<Long> ingredientsIds = ingredientAmountMap.keySet().stream().map(Ingredient::getId).collect(Collectors.toSet());
            assertTrue(ingredientsIds.contains(7L));
        }
    }

    @Test
    void findLikedRecipes() {
        User user = new User();
        user.setId(4L);
        user.setEmail("some@mail.com");
        user.setUsername("Username");
        user.setPassword("Password1@");
        user.setBirthDate(LocalDate.of(1993, 4, 15));
        Set<Recipe> liked = new HashSet<>();
        liked.add(recipeRepository.getReferenceById(1L));
        liked.add(recipeRepository.getReferenceById(4L));
        user.setLikedRecipes(liked);
        userRepository.save(user);
        Set<Recipe> actual = recipeRepository.findLikedRecipes(4L);
        assertEquals(liked.size(), actual.size());
        assertTrue(actual.containsAll(liked));
    }

    @Test
    void findSavedRecipes() {
        User user = new User();
        user.setId(5L);
        user.setEmail("some@mail.com");
        user.setUsername("Username");
        user.setPassword("Password1@");
        user.setBirthDate(LocalDate.of(1993, 4, 15));
        Set<Recipe> saved = new HashSet<>();
        saved.add(recipeRepository.getReferenceById(1L));
        saved.add(recipeRepository.getReferenceById(3L));
        user.setSavedRecipes(saved);
        userRepository.save(user);
        Set<Recipe> actual = recipeRepository.findSavedRecipes(5L);
        assertEquals(saved.size(), actual.size());
        assertTrue(actual.containsAll(saved));
    }
}