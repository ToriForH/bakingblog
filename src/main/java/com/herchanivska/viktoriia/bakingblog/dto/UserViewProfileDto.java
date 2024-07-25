package com.herchanivska.viktoriia.bakingblog.dto;

import com.herchanivska.viktoriia.bakingblog.model.Recipe;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserViewProfileDto {
    private long id;
    private String username;
    private LocalDate birthDate;
    private long followersCount;
    private List<Recipe> userRecipes;
}
