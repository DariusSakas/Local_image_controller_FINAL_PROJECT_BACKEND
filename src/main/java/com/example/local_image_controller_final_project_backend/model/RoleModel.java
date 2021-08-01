package com.example.local_image_controller_final_project_backend.model;

import javax.persistence.*;

@Entity
@Table(name = "role_model")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public RoleModel() {
    }

    public RoleModel(Long id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public RoleModel(ERole eRole) {
        this.name = eRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
