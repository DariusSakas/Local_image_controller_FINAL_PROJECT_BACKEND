package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.model.UserModel;
import com.example.local_image_controller_final_project_backend.repository.UserModelRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserModelService implements UserDetailsService {

    private final UserModelRepository userRepository;
    
    public UserModelService(UserModelRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserModel userModel = userRepository.findUserByUsername(userName);
        if (userModel == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userModel;
    }
}
