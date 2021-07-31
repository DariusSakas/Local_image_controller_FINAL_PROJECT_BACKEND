package com.example.local_image_controller_final_project_backend.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class PrivilegeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<RoleModel> roles;

    public PrivilegeModel() {
    }

    public PrivilegeModel(String name) {
        this.name = name;
    }

    public PrivilegeModel(Long id, String name, Collection<RoleModel> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
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

    public Collection<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleModel> roles) {
        this.roles = roles;
    }
}
