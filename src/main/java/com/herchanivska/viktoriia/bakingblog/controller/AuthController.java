package com.herchanivska.viktoriia.bakingblog.controller;

import com.herchanivska.viktoriia.bakingblog.dto.UserSignInDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserSignUpDto;
import com.herchanivska.viktoriia.bakingblog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class AuthController {
    UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new UserSignUpDto());
        return "register-user";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserSignUpDto user, BindingResult result) {
        if (result.hasErrors()) {
            return "register-user";
        }
        userService.save(user);
        return "redirect:/signin";
    }

    @GetMapping("/signin")
    public String signin(Model model) {
        model.addAttribute("user", new UserSignInDto());
        return "signin-user";
    }
}
