package com.example.local_image_controller_final_project_backend.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user_model",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private String roles = "";

    public UserModel() {
    }

    public UserModel(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getRoles() {
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public void setRoles(List<String> rolesList) {
        this.roles = String.join(", ", rolesList);
    }
}
