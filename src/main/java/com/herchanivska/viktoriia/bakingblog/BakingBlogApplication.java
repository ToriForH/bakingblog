package com.herchanivska.viktoriia.bakingblog;

import com.herchanivska.viktoriia.bakingblog.model.Recipe;
import com.herchanivska.viktoriia.bakingblog.repository.IngredientRepository;
import com.herchanivska.viktoriia.bakingblog.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BakingBlogApplication {
	public static void main(String[] args) {
		SpringApplication.run(BakingBlogApplication.class, args);
	}
}
