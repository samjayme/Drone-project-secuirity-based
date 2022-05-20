package com.samuelspringboot.dronespringboot.configuration;

import com.samuelspringboot.dronespringboot.service.JwtService;
import com.samuelspringboot.dronespringboot.utill.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        String jwt_token = null;
        String userName = null;
        if (header != null && header.startsWith("Bearer ")) {
            jwt_token = header.substring(7);

            try {
                userName = jwtUtil.getUserNameFromToken(jwt_token);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get Jwt token");
            } catch (ExpiredJwtException e) {
                System.out.println("Jwt token expired");
            }

        }else {
            System.out.println("jwt token does not start with Bearer");
        }

        if (userName!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = jwtService.loadUserByUsername(userName);
            if (jwtUtil.validateToken(jwt_token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

}
