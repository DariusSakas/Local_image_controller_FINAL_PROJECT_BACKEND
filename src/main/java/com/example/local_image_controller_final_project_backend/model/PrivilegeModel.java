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
}
