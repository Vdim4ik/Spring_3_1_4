package com.example.spring_3_1_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.spring_3_1_4.model.User;
import com.example.spring_3_1_4.service.UserServiceImp;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/user")
    public String getUserPage(Principal principal, ModelMap modelMap) {
        User user = userServiceImp.findByUsername(principal.getName());
        modelMap.addAttribute("user", user);
        return "user";
    }
}
