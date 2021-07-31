package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.model.RoleModel;
import com.example.local_image_controller_final_project_backend.model.UserModel;
import com.example.local_image_controller_final_project_backend.model.UserModelImpl;
import com.example.local_image_controller_final_project_backend.repository.RoleModelRepository;
import com.example.local_image_controller_final_project_backend.repository.UserModelRepository;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserModelDetailsService implements UserDetailsService {

    private final UserModelRepository userRepository;

    public UserModelDetailsService(UserModelRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * When you annotate a method with @Transactional, Spring dynamically creates a
     * proxy that implements the same interface(s) as the class you're annotating.
     * And when clients make calls into your object,
     * the calls are intercepted and the behaviors injected via the proxy mechanism.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserModelImpl.getUserModelImpl(userModel);
    }
}
