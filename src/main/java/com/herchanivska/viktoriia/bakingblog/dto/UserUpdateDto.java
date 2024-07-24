package com.herchanivska.viktoriia.bakingblog.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserUpdateDto {
    private String email;
    private String username;
    private LocalDate birthDate;
}
