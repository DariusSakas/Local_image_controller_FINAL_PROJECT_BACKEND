package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.model.UserModel;
import com.example.local_image_controller_final_project_backend.repository.UserModelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserModelService {
    private final UserModelRepository userModelRepository;

    public UserModelService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    public void saveUserToDB(UserModel user) {
        userModelRepository.save(user);
    }

    public Optional<UserModel> findUserByUsername(String username) {
        return userModelRepository.findByUsername(username);
    }
}
