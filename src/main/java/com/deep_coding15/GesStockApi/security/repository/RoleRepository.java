package com.deep_coding15.GesStockApi.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    <Optional> Role findByCode(String code);

    boolean existsByCode(String code);
}
