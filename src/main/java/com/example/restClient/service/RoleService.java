package com.example.restClient.service;

import com.example.restClient.entity.Roles;
import com.example.restClient.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public void addRole(Roles role) {
        roleRepository.save(role);
    }
}
