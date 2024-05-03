package com.herchanivska.viktoriia.bakingblog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Recipe name can not be null or empty")
    @Pattern(regexp = "[a-zA-Z ]+", message = "Recipe name can contain only letters and spaces")
    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ElementCollection
    @CollectionTable(
            name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id")
    )
    @MapKeyJoinColumn(name = "ingredient_id")
    private Map<Ingredient, IngredientAmount> ingredients;

    @OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private RecipeCooking recipeCooking;

    @Column(name = "cooking_time_in_minutes")
    @Min(value = 1, message = "Cooking time should be at least 1 minute")
    private int cookingTimeMinutes;
}
