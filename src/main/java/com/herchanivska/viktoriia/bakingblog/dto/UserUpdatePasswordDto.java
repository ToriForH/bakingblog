package com.herchanivska.viktoriia.bakingblog.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserUpdatePasswordDto {
    private String oldPassword;
    private String password;
    private String submitPassword;
}
