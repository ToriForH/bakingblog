package com.herchanivska.viktoriia.bakingblog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotNull(message = "Email can not be null")
    @Pattern(regexp = "^[\\w.-]+@([\\w-]+\\.)+\\w{2,4}$", message = "Please provide valid email")
    private String email;

    @Column(unique = true)
    @NotNull(message = "Username can not be null")
    @Pattern(regexp = "[\\w-]+", message = "Username could contain only letters, numbers, dash and underscore")
    @Size(min = 3, max = 25, message = "Username should be more than or equal to {min} and less than or equal to {max}")
    private String username;

    @NotNull(message = "Password can not be null")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$",
            message = "Password should contain at least one lower case letter, " +
                    "one upper case letter, one digit, one special character")
    @Size(min = 8, message = "Password should be at least {min} characters in length")
    private String password;

    @Past(message = "Birth date should be before today")
    private LocalDate birthDate;

    @OneToMany(targetEntity = Recipe.class, mappedBy = "author")
    private List<Recipe> myRecipes;

    @ManyToMany(targetEntity = Recipe.class)
    @JoinTable(name = "saved_recipes",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private Set<Recipe> savedRecipes;

    @ManyToMany(targetEntity = Recipe.class)
    @JoinTable(name = "liked_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private List<Recipe> likedRecipes;

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "user_followers",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"))
    private List<User> followers;
}
