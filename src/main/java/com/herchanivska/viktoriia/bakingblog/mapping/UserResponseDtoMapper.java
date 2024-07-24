package com.herchanivska.viktoriia.bakingblog.mapping;

import com.herchanivska.viktoriia.bakingblog.dto.UserResponseDto;
import com.herchanivska.viktoriia.bakingblog.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDtoMapper extends AbstractConverter<User, UserResponseDto> {
    @Override
    protected UserResponseDto convert(User value) {
        return UserResponseDto.builder()
                .email(value.getEmail())
                .username(value.getUsername())
                .birthDate(value.getBirthDate())
                .build();
    }
}
