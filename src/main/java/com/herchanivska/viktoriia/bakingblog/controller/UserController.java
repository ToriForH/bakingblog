package com.herchanivska.viktoriia.bakingblog.controller;

import com.herchanivska.viktoriia.bakingblog.dto.UserSignUpDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserUpdateDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserUpdatePasswordDto;
import com.herchanivska.viktoriia.bakingblog.model.User;
import com.herchanivska.viktoriia.bakingblog.security.CustomUserDetails;
import com.herchanivska.viktoriia.bakingblog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("user", userService.findById(userId));
        return "update-user";
    }

    @GetMapping("/profile/edit")
    public String updateMyProfile(Model model, Authentication authentication) {
        Long currentUserId = ((CustomUserDetails) authentication.getDetails()).getUser().getId();
        model.addAttribute("user", userService.findById(currentUserId));
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

    // view profile page view other user profile page User findById(Long id);
    //find user to follow User findByUsername(String username);
    //follow/unfollow user Set<User> findFollowers(Long id);
    //view all users List<User> findAll();
}
