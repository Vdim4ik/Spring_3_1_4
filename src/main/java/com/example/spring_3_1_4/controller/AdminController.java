package com.example.spring_3_1_4.controller;

import com.example.spring_3_1_4.service.RoleService;
import com.example.spring_3_1_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.spring_3_1_4.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAdminPage(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userService.findByUsername(principal.getName()));
        modelMap.addAttribute("userList", userService.getUserList());
        modelMap.addAttribute("roleList", roleService.getRoleList());
        return "admin";
    }

    @GetMapping("/new")
    public String getPageToAddNewUser(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userService.findByUsername(principal.getName()));
        modelMap.addAttribute("newUser", new User());
        modelMap.addAttribute("roleList", roleService.getRoleList());
        return "new";
    }

    @PostMapping("/new")
    public String addNewUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/update/{username}")
    public String updateUser(@ModelAttribute User user, @PathVariable("username") String username) {
        userService.updateUser(username, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
