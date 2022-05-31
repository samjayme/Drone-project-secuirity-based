package com.samuelspringboot.dronespringboot.repository;
import com.samuelspringboot.dronespringboot.entity.User;
import com.samuelspringboot.dronespringboot.service.RolesService;
import com.samuelspringboot.dronespringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesService rolesService;



    @PostMapping("/SignUpAdmin")
    public ResponseEntity<User> addAdmin(@RequestBody User u) {

        return new ResponseEntity<>(userService.addAdmin(u), HttpStatus.CREATED);
    }

    @PostMapping("/SignUp")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerNewUser(user), HttpStatus.CREATED);
    }




    }