package com.herchanivska.viktoriia.bakingblog.mapping;

import com.herchanivska.viktoriia.bakingblog.dto.UserViewProfileDto;
import com.herchanivska.viktoriia.bakingblog.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserViewProfileDtoMapper extends AbstractConverter<User, UserViewProfileDto> {
    @Override
    protected UserViewProfileDto convert(User value) {
        return UserViewProfileDto.builder()
                .id(value.getId())
                .username(value.getUsername())
                .birthDate(value.getBirthDate())
                .followersCount(value.getFollowers().size())
                .userRecipes(value.getMyRecipes() == null ? null : value.getMyRecipes().stream().toList())
                .build();
    }
}
