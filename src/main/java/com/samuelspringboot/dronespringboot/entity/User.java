package com.samuelspringboot.dronespringboot.entity;

import com.samuelspringboot.dronespringboot.entity.Role;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @Column(name = "user_Id")
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long user_Id;
    private String userName;
    private String passWord;
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    public User(Long user_Id, String userName, String passWord, String email, List<Role> roles) {
        this.user_Id = user_Id;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.roles = roles;
    }

    public User(){

    }

    public Long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
