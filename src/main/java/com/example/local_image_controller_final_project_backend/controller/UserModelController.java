package com.example.local_image_controller_final_project_backend.controller;

import com.example.local_image_controller_final_project_backend.jwt.JWTUtils;
import com.example.local_image_controller_final_project_backend.service.RoleModelService;
import com.example.local_image_controller_final_project_backend.service.UserModelService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserModelController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserModelService userModelService;
    private final RoleModelService roleModelService;
    private final JWTUtils jwtUtils;

    public UserModelController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserModelService userModelService, RoleModelService roleModelService, JWTUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userModelService = userModelService;
        this.roleModelService = roleModelService;
        this.jwtUtils = jwtUtils;
    }


}
