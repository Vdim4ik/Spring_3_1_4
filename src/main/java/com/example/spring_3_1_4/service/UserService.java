package com.example.spring_3_1_4.service;

import com.example.spring_3_1_4.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUserList();

    void addUser(User user);

    void updateUser(String username, User updateUser);

    void deleteUser(long id);

    User findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username);

}
