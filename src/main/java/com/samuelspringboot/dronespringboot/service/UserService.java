package com.samuelspringboot.dronespringboot.service;

import com.samuelspringboot.dronespringboot.entity.User;


import java.util.List;

public interface UserService {

     User addAdmin(User u);
     void  initRoleAndAdmin();

     User registerNewUser(User user);


     User addRoleToUser(String username, List<Long> Id);


}
