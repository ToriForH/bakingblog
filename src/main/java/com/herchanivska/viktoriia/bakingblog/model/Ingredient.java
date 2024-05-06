package com.herchanivska.viktoriia.bakingblog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotNull(message = "Ingredient name can not be null")
    @Pattern(regexp = "[a-zA-Z0-9-]+( [a-zA-Z0-9-]+)*", message = "Ingredient name should contain only letters, numbers, dash or spaces between words")
    private String name;
}
