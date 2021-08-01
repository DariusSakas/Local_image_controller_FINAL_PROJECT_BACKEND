package com.example.local_image_controller_final_project_backend.controller;

import com.example.local_image_controller_final_project_backend.exceptions.UserAlreadyTakenException;
import com.example.local_image_controller_final_project_backend.jwt.JWTUtils;
import com.example.local_image_controller_final_project_backend.jwt.jwtModel.JWTResponse;
import com.example.local_image_controller_final_project_backend.jwt.jwtModel.LoginRequest;
import com.example.local_image_controller_final_project_backend.jwt.jwtModel.SignUpRequest;
import com.example.local_image_controller_final_project_backend.model.ERole;
import com.example.local_image_controller_final_project_backend.model.RoleModel;
import com.example.local_image_controller_final_project_backend.model.UserModel;
import com.example.local_image_controller_final_project_backend.model.UserModelImpl;
import com.example.local_image_controller_final_project_backend.service.RoleModelService;
import com.example.local_image_controller_final_project_backend.service.UserModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class UserModelAndAuthenticationController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserModelService userModelService;
    private final RoleModelService roleModelService;
    private final JWTUtils jwtUtils;

    public UserModelAndAuthenticationController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserModelService userModelService, RoleModelService roleModelService, JWTUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userModelService = userModelService;
        this.roleModelService = roleModelService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTResponse> getUserAuthentication(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserModelImpl userDetails = (UserModelImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new JWTResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {

        Set<RoleModel> roles = new HashSet<>();
        Set<String> signUpRequestRole = signUpRequest.getRole();

        //Check if UserName is taken (DB check)
        UserModel userModel = userModelService.findUserByUsername(signUpRequest.getUsername()).orElse(null);
        if (userModel != null) {
            return ResponseEntity.badRequest().body("Username already taken!");
        }

        // Create new userModel object with password and username from client:
        UserModel user = new UserModel(signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        if (signUpRequestRole == null) {
            RoleModel userRole = roleModelService.findRoleByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            signUpRequestRole.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleModel adminRole = roleModelService.findRoleByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        RoleModel modRole = roleModelService.findRoleByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        RoleModel userRole = roleModelService.findRoleByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userModelService.saveUserToDB(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }


}
