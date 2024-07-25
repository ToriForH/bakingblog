package com.herchanivska.viktoriia.bakingblog.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class UserSearchDto {
    private long id;
    private String username;
    private long followersCount;
}