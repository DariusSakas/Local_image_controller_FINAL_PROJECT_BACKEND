package com.example.local_image_controller_final_project_backend.model;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role_model")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserModel> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<PrivilegeModel> privileges;

    public RoleModel(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserModel> users) {
        this.users = users;
    }

    public Collection<PrivilegeModel> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<PrivilegeModel> privileges) {
        this.privileges = privileges;
    }
}
