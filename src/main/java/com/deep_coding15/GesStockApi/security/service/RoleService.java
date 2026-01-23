package com.deep_coding15.GesStockApi.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
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
            throw new EntityAlreadyExistsException(
                "Role", "code", 
                role.getCode());
        }

        return roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Il faut un id pour chercher le rôle.");
        }

        return roleRepository.getReferenceById(id);
    }

    public Role getRoleByCode(String code) {

        if (code == null || code.isBlank())

        {
            throw new IllegalArgumentException("Le code est obligatoire");
        }

        Role role = roleRepository.findByCode(code);

        if (role == null) {
            throw new IllegalArgumentException("Rôle introuvable");
        }
        return role;
    }

    public Set<Role> getRoles() {
        return new HashSet<>(roleRepository.findAll());
    }
}
