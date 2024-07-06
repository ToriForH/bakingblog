package com.herchanivska.viktoriia.bakingblog.controller;

import com.herchanivska.viktoriia.bakingblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @PostMapping({"/", "/home"})
    public String home() {
        return "home";
    }
}
