package com.herchanivska.viktoriia.bakingblog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe_cooking_steps")
public class RecipeCooking {
    @Id
    @Column(name = "recipe_id")
    private long recipeId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @NotEmpty(message = "Cooking steps can not be null or empty")
    @Lob
    @Column(name = "steps", columnDefinition = "TEXT")
    private String cookingSteps; //will be serialized as List<String> to type JSON
}
