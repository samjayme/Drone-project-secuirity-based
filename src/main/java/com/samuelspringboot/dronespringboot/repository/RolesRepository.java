package com.samuelspringboot.dronespringboot.repository;

import com.samuelspringboot.dronespringboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RolesRepository extends JpaRepository<Role,Long> {
    Role findRoleByRole(String role);


}
