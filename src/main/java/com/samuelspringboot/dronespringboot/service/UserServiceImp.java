package com.samuelspringboot.dronespringboot.service;

import com.samuelspringboot.dronespringboot.entity.Role;
import com.samuelspringboot.dronespringboot.entity.User;
import com.samuelspringboot.dronespringboot.repository.RolesRepository;
import com.samuelspringboot.dronespringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User addAdmin(User u) {
        List<Role> roleList = new ArrayList<>();
        Role role = rolesRepository.findRoleByRole("ADMIN");


        roleList.add(role);

        u.setRoles(roleList);
        u.setPassWord(passwordEncoder.encode(u.getPassWord()));

        return userRepository.save(u);
    }

    @Override
    public void initRoleAndAdmin() {
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        rolesRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRole("USER");
        rolesRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("samjayme");
        adminUser.setPassWord(passwordEncoder.encode("sintax80"));
        adminUser.setEmail("samuelwisdom98@gmail.com");
        List<Role> adminRoleList = new ArrayList<>();
        adminRoleList.add(adminRole);
        userRepository.save(adminUser);
    }

    @Override
    public User registerNewUser(User user) {
        List<Role> roleList = new ArrayList<>();
        Role role = rolesRepository.findRoleByRole("USER");


        roleList.add(role);

        user.setRoles(roleList);
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));

        return userRepository.save(user);
    }

    @Override
    public User addRoleToUser(String username, List<Long> Id) {
        User user = userRepository.findAll().stream().filter(u-> u.getUserName().equals(username)).findAny().orElseThrow(()-> new
                UsernameNotFoundException("User with username " + username + "not found"));
        List<Role> roleList = rolesRepository.findAllById(Id);

        user.setRoles(roleList);
        userRepository.save(user);
        return user;
    }


}
