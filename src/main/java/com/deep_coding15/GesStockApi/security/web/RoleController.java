package com.deep_coding15.GesStockApi.security.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    public final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    public ResponseEntity<Role> creerRole(@RequestBody Role role) {
        Role roleCree = roleService.createRole(role);
        return new ResponseEntity<>(roleCree, HttpStatus.CREATED);
    }

}
