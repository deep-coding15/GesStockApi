package com.deep_coding15.GesStockApi.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.utils.Utils;

import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role createRole(Role role) {

        if(Utils.isStringUseless(role.getCode()) 
            || Utils.isStringUseless(role.getLibelle())) {
                throw new EntityIllegalArgumentException(
                    "Role", "",
                    "", "le code et le libelle doivent etre fournis.");
        }

        if (roleRepository.existsByCode(role.getCode())) {
            throw new EntityAlreadyExistsException(
                "Role", "code", 
                role.getCode());
        }

        return roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        if (Utils.isNegativeOrNullOrZero(id)) {
            throw new EntityIllegalArgumentException("Role", "id", id.toString());
        }

        return roleRepository.getReferenceById(id);
    }

    public Role getRoleByCode(String code) {

        if (Utils.isStringUseless(code))
            throw new EntityIllegalArgumentException("Role", "code", code);


        Role role = roleRepository.findByCode(code);            

        if (role == null) {
            throw new EntityNotFoundException("Role", "code", code);
        }
        return role;
    }

    public Set<Role> getRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Transactional
    public Role patchRole(Long id, Role role){

        if(Utils.isNegativeOrNullOrZero(id))
            throw new EntityIllegalArgumentException("Role", "id", id);

        Role roleOptional = roleRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Role", "id", id.toString()));

        if(role.getCode() != null)
            roleOptional.setCode(role.getCode());

        if(role.getLibelle() != null)
            roleOptional.setLibelle(role.getLibelle());

        return roleRepository.save(roleOptional);
    }
    
    @Transactional
    public Role putRole(Long id, Role role){

        if(Utils.isNegativeOrNullOrZero(id))
            throw new EntityIllegalArgumentException("Role", "id", id);

        Role roleOptional = roleRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Role", "id", id.toString()));

        roleOptional.setCode(role.getCode());

        roleOptional.setLibelle(role.getLibelle());

        return roleRepository.save(roleOptional);
    }

    @Transactional
    public void deleteRole(Long id){
        if(Utils.isNegativeOrNullOrZero(id))
            throw new EntityIllegalArgumentException("Role", "id", id);

        if(!roleRepository.existsById(id))
            throw new EntityNotFoundException("Role", "id", id.toString());

        roleRepository.deleteById(id);
        return;
    }
}
