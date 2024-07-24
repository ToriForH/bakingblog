package com.herchanivska.viktoriia.bakingblog.service;

import com.herchanivska.viktoriia.bakingblog.dto.UserResponseDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserSignUpDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserUpdateDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserUpdatePasswordDto;
import com.herchanivska.viktoriia.bakingblog.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User save(UserSignUpDto user);
    User update(UserUpdateDto user, Long id);
    User updatePassword(UserUpdatePasswordDto dto, Long id);
    void delete(Long id);
    UserResponseDto findById(Long id);
    User findByEmail(String email);
    User findByUsername(String username);
    Set<User> findFollowers(Long id);
    List<User> findAll();
}
