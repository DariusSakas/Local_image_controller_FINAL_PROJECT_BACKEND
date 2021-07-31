package com.example.local_image_controller_final_project_backend.repository;

import com.example.local_image_controller_final_project_backend.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleModelRepository extends JpaRepository<RoleModel, Long> {

    RoleModel findByName(String name);
}
