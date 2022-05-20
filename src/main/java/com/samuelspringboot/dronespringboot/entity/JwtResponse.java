package com.samuelspringboot.dronespringboot.entity;

public class JwtResponse {
    private User user;
    private String jwt_token;

    public JwtResponse(User user, String jwt_token) {
        this.user = user;
        this.jwt_token = jwt_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt_token() {
        return jwt_token;
    }

    public void setJwt_token(String jwt_token) {
        this.jwt_token = jwt_token;
    }
}
