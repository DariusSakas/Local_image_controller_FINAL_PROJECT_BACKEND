package com.example.local_image_controller_final_project_backend.repository;

import com.example.local_image_controller_final_project_backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUsername (String userName);

}
