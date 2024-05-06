package com.herchanivska.viktoriia.bakingblog.repository;

import com.herchanivska.viktoriia.bakingblog.model.Recipe;
import com.herchanivska.viktoriia.bakingblog.model.RecipeCooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeCookingRepository extends JpaRepository<RecipeCooking, Recipe> {
}
