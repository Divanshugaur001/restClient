package com.example.restClient.controller;

import com.example.restClient.entity.Roles;
import com.example.restClient.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Roles roles){
            roleService.addRole(roles);
            return ResponseEntity.ok("Role is add");
    }
}
