package com.herchanivska.viktoriia.bakingblog.mapping;

import com.herchanivska.viktoriia.bakingblog.dto.UserSearchDto;
import com.herchanivska.viktoriia.bakingblog.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserSearchDtoMapper extends AbstractConverter<User, UserSearchDto> {
    @Override
    protected UserSearchDto convert(User value) {
        return UserSearchDto.builder()
                .id(value.getId())
                .username(value.getUsername())
                .followersCount(value.getFollowers().size())
                .build();
    }
}