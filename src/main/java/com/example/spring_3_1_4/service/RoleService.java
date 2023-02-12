package com.example.spring_3_1_4.service;

import com.example.spring_3_1_4.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoleList();

    void addRole(Role role);

}
