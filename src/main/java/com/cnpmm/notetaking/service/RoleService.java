package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.Role;
import com.cnpmm.notetaking.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void setRole(Role role){
        roleRepository.save(role);
    }

    public Optional<Role> findRoleByName(String name){
        return roleRepository.findRoleByName(name);
    }
}
