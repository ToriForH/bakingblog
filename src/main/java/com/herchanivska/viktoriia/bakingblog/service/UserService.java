package com.herchanivska.viktoriia.bakingblog.service;

import com.herchanivska.viktoriia.bakingblog.dto.*;
import com.herchanivska.viktoriia.bakingblog.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User save(UserSignUpDto user);
    User update(UserUpdateDto user, Long id);
    User updatePassword(UserUpdatePasswordDto dto, Long id);
    void delete(Long id);
    UserUpdateDto findByIdForUpdate(Long id);
    UserViewProfileDto getUserProfile(Long id);
    User findByEmail(String email);
    List<UserSearchDto> findAllByUsername(String username);
    void followUser(Long currentUserId, Long userToFollowId);
    void unfollowUser(Long currentUserId, Long userToUnfollowId);
    Set<UserSearchDto> findFollowers(Long id);
    List<UserSearchDto> findAll();
}
