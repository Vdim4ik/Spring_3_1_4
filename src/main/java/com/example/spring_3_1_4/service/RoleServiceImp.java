package com.example.spring_3_1_4.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_3_1_4.repositories.RoleRepository;
import com.example.spring_3_1_4.model.Role;
import java.util.List;

@Service
public class RoleServiceImp {

    private RoleRepository roleRepository;
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

}
