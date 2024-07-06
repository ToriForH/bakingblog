package com.herchanivska.viktoriia.bakingblog.controller;

import com.herchanivska.viktoriia.bakingblog.dto.UserSignInDto;
import com.herchanivska.viktoriia.bakingblog.dto.UserSignUpDto;
import com.herchanivska.viktoriia.bakingblog.exception.NotFoundException;
import com.herchanivska.viktoriia.bakingblog.exception.WrongPasswordException;
import com.herchanivska.viktoriia.bakingblog.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

    AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
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

    @PostMapping("/signin")
    public String login(Model model, HttpSession httpSession, @ModelAttribute UserSignInDto user, HttpServletResponse response) {
        try {
            String token = userService.signIn(user);
            Cookie cookie = new Cookie("accessToken", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/home";
        } catch (NotFoundException | WrongPasswordException e) {
            httpSession.setAttribute("error", true);
            return "/signin";
        } catch (Exception e) {
            httpSession.setAttribute("message", "Something go wrong");
            return "/signin";
        }
    }

    @GetMapping("/token")
    public String token(Model model, HttpServletRequest request) {
        model.addAttribute("code", "Token:");
        model.addAttribute("message", userService.getToken(request));
        return "error";
    }
}
