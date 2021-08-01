package com.example.local_image_controller_final_project_backend.jwt.jwtModel;

import java.util.Set;

public class SignUpRequest {

    private String username;
    private Set<String> role;
    private String password;

    public SignUpRequest(String username, Set<String> role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

}
