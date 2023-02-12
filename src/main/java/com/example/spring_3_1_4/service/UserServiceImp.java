package com.example.spring_3_1_4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.spring_3_1_4.model.User;
import com.example.spring_3_1_4.repositories.UserRepository;
import java.util.List;

@Service
public class UserServiceImp implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(String username, User updateUser) {
        User user = findByUsername(username);
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setUsername(updateUser.getUsername());
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        user.setAge(updateUser.getAge());
        user.setRoles(updateUser.getRoles());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
}
