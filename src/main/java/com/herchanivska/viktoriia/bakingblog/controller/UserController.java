package com.herchanivska.viktoriia.bakingblog.controller;

import com.herchanivska.viktoriia.bakingblog.dto.*;
import com.herchanivska.viktoriia.bakingblog.model.User;
import com.herchanivska.viktoriia.bakingblog.security.CustomUserDetails;
import com.herchanivska.viktoriia.bakingblog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new-user") //admin only
    public String create(Model model) {
        model.addAttribute("user", new UserSignUpDto());
        return "register-user";
    }

    @PostMapping("/new-user") //admin only
    public String create(@Valid UserSignUpDto user, BindingResult result) {
        if (result.hasErrors()) {
            return "register-user";
        }
        User savedUser = userService.save(user);
        return "redirect:/users/" + savedUser.getId();
    }

    @GetMapping("/edit/{userId}")
    public String update(Model model, @PathVariable Long userId) {
        model.addAttribute("user", userService.findByIdForUpdate(userId));
        return "update-user";
    }

    @GetMapping("/profile/edit")
    public String updateMyProfile(Model model, Authentication authentication) {
        Long currentUserId = ((CustomUserDetails) authentication.getDetails()).getUser().getId();
        model.addAttribute("user", userService.findByIdForUpdate(currentUserId));
        model.addAttribute("userId", currentUserId);
        return "update-user";
    }

    @PostMapping("/edit/{userId}")
    public String update(@Valid UserUpdateDto user, @PathVariable Long userId, BindingResult result) {
        if (result.hasErrors()) {
            return "update-user";
        }
        User updatedUser = userService.update(user, userId);
        return "redirect:/users/" + updatedUser.getId();
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        model.addAttribute("passwordDto", new UserUpdatePasswordDto());
        return "change-password-user";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid UserUpdatePasswordDto dto, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
        User updatedUser = userService.updatePassword(dto, userDetails.getUser().getId());
        return "redirect:/users/" + updatedUser.getId();
    }

    @GetMapping("/delete/{userId}")
    public String delete(@PathVariable Long userId, Authentication authentication) {
        Long currentUserId = ((CustomUserDetails) authentication.getDetails()).getUser().getId();
        userService.delete(userId);
        if (currentUserId.equals(userId)) return "redirect:/logout";
        return "redirect:/users/all";
    }

    @GetMapping("/profile/delete")
    public String deleteMyProfile(Authentication authentication) {
        long currentUserId = ((CustomUserDetails) authentication.getDetails()).getUser().getId();
        return "redirect:/users/delete/" + currentUserId;
    }

    @GetMapping("/{userId}")
    public String getById(Model model, @PathVariable Long userId) {
        UserViewProfileDto user = userService.getUserProfile(userId);
        model.addAttribute("user", user);
        return "view-user";
    }

    @GetMapping("/profile/my")
    public String myProfile(Authentication authentication) {
        long currentUserId = ((CustomUserDetails) authentication.getDetails()).getUser().getId();
        return "redirect:/users/" + currentUserId;
    }

    @GetMapping("/search")
    public String searchByUsername(Model model, @RequestParam String searchedString) {
        List<UserSearchDto> users = userService.findAllByUsername(searchedString);
        model.addAttribute("users", users);
        return "search-user";
    }

    @GetMapping("/follow/{userId}")
    public String follow(@PathVariable Long userId, Authentication authentication) {
        long currentUserId = ((CustomUserDetails) authentication.getDetails()).getUser().getId();
        userService.followUser(currentUserId, userId);
        return "redirect:/users/" + userId;
    }

    @GetMapping("/unfollow/{userId}")
    public String unfollow(@PathVariable Long userId, Authentication authentication) {
        long currentUserId = ((CustomUserDetails) authentication.getDetails()).getUser().getId();
        userService.unfollowUser(currentUserId, userId);
        return "redirect:/users/" + userId;
    }

    @GetMapping("/{userId}/followers")
    public String viewFollower(Model model, @PathVariable Long userId) {
        Set<UserSearchDto> users = userService.findFollowers(userId);
        model.addAttribute("users", users);
        return "search-user";
    }

    @GetMapping("/all") //admin only
    public String getAll(Model model) {
        List<UserSearchDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "search-user";
    }
}
