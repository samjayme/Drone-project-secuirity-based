package com.samuelspringboot.dronespringboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    public Long getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(Long role_Id) {
        this.role_Id = role_Id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_Id;
    private String role;


}
