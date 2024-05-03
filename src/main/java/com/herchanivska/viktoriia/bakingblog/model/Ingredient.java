package com.herchanivska.viktoriia.bakingblog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Ingredient name can not be null or empty")
    @Pattern(regexp = "[a-zA-Z0-9 -]+", message = "Ingredient name should contain only letters, numbers or dash")
    private String name;
}
