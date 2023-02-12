package com.example.spring_3_1_4.controller;

import com.example.spring_3_1_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.findByEmail(principal.getName()));
        return "user";
    }
}
