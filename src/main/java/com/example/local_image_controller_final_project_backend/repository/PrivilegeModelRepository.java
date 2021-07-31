package com.example.local_image_controller_final_project_backend.repository;

import com.example.local_image_controller_final_project_backend.model.PrivilegeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeModelRepository extends JpaRepository<PrivilegeModel, Long> {
    PrivilegeModel findByName(String name);


}
