package com.herchanivska.viktoriia.bakingblog.service;

import com.herchanivska.viktoriia.bakingblog.dto.UserSignInDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserSignUpDto;
import com.herchanivska.viktoriia.bakingblog.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Set;

public interface UserService {
    User save(UserSignUpDto user);
    User update(User user);
    void delete(Long id);
    User findById(Long id);
    User findByEmail(String email);
    User findByUsername(String username);
    Set<User> findFollowers(Long id);
    List<User> findAll();
    String signIn(UserSignInDto user);
    String getToken(HttpServletRequest request);
}
