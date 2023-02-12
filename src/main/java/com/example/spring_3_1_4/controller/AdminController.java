package com.example.spring_3_1_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.spring_3_1_4.model.User;
import com.example.spring_3_1_4.service.UserServiceImp;
import com.example.spring_3_1_4.service.RoleServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.security.Principal;

@Controller
public class AdminController {

    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;

    @Autowired
    public AdminController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/admin")
    public String getAdminPage(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userServiceImp.findByUsername(principal.getName()));
        modelMap.addAttribute("userList", userServiceImp.getUserList());
        modelMap.addAttribute("roleList", roleServiceImp.getRoleList());
        return "admin";
    }

    @GetMapping("/new")
    public String getPageToAddNewUser(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userServiceImp.findByUsername(principal.getName()));
        modelMap.addAttribute("newUser", new User());
        modelMap.addAttribute("roleList", roleServiceImp.getRoleList());
        return "new";
    }

    @PostMapping("/new")
    public String addNewUser(@ModelAttribute User user) {
        userServiceImp.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/update/{username}")
    public String updateUser(@ModelAttribute User user, @PathVariable("username") String username) {
        userServiceImp.updateUser(username, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImp.deleteUser(id);
        return "redirect:/admin";
    }
}
