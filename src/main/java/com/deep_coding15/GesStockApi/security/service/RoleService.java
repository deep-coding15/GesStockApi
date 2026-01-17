package com.deep_coding15.GesStockApi.security.service;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {

        if (roleRepository.existsByCode(role.getCode())) {
            throw new IllegalArgumentException("Un code avec cette référence existe déjà.");
        }

        return roleRepository.save(role);
    }
}
