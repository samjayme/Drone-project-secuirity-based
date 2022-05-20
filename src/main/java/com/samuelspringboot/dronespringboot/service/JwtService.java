package com.samuelspringboot.dronespringboot.service;

import com.samuelspringboot.dronespringboot.entity.JwtRequest;
import com.samuelspringboot.dronespringboot.entity.JwtResponse;
import com.samuelspringboot.dronespringboot.entity.User;
import com.samuelspringboot.dronespringboot.repository.UserRepository;
import com.samuelspringboot.dronespringboot.utill.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username).get();
        if (user !=null){
            return  new org.springframework.security.core.userdetails.User(user.getUserName(),
                    user.getPassWord(),
                    getAuthority(user));
        }else {
            throw new UsernameNotFoundException("UserName not valid");
        }

    }

    private List getAuthority(User user){
        List authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });
        return authorities;
    }

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
       String userName = jwtRequest.getUserName();
       String userPassWord = jwtRequest.getUserPassword();
       authenticate(userName,userPassWord);

      final UserDetails userDetails = loadUserByUsername(userName);
      String newGeneratedToken = jwtUtil.generateToken(userDetails);
      User user = userRepository.findUserByUserName(userName).get();

      return  new JwtResponse(user,newGeneratedToken);

    }

    private   void authenticate(String userName, String userPassWord) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassWord));

        }catch (DisabledException e){
            throw  new Exception("User disabled");
        }catch (BadCredentialsException e){
            throw  new Exception("Bad Credentials from user");
        }

    }
}
