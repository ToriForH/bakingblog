package com.herchanivska.viktoriia.bakingblog.mapping;

import com.herchanivska.viktoriia.bakingblog.dto.UserUpdateDto;
import com.herchanivska.viktoriia.bakingblog.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserFromUpdateMapper extends AbstractConverter<UserUpdateDto, User> {
    @Override
    protected User convert(UserUpdateDto value) {
        return User.builder()
                .email(value.getEmail())
                .username(value.getUsername())
                .birthDate(value.getBirthDate())
                .build();
    }
}