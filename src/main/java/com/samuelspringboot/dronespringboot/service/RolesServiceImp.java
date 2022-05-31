package com.samuelspringboot.dronespringboot.service;

import com.samuelspringboot.dronespringboot.entity.Role;
import com.samuelspringboot.dronespringboot.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImp implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Role addRoles(Role role) {
        return rolesRepository.save(role);
    }
}
