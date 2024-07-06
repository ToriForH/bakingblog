package com.herchanivska.viktoriia.bakingblog.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserSignInDto {
    String username;
    String password;
}
