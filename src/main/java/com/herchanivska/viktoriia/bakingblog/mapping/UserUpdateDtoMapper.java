package com.herchanivska.viktoriia.bakingblog.mapping;

import com.herchanivska.viktoriia.bakingblog.dto.UserUpdateDto;
import com.herchanivska.viktoriia.bakingblog.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateDtoMapper extends AbstractConverter<User, UserUpdateDto> {
    @Override
    protected UserUpdateDto convert(User value) {
        return UserUpdateDto.builder()
                .email(value.getEmail())
                .username(value.getUsername())
                .birthDate(value.getBirthDate())
                .build();
    }
}
