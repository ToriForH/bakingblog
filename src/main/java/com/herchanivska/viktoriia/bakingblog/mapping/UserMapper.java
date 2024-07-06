package com.herchanivska.viktoriia.bakingblog.mapping;

import com.herchanivska.viktoriia.bakingblog.dto.UserSignUpDto;
import com.herchanivska.viktoriia.bakingblog.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractConverter<UserSignUpDto, User> {
    @Override
    protected User convert(UserSignUpDto value) {
        return User.builder()
                .email(value.getEmail())
                .username(value.getUsername())
                .birthDate(value.getBirthDate())
                .password(value.getPassword())
                .build();
    }
}
