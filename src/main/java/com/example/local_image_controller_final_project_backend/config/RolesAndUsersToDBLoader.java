package com.example.local_image_controller_final_project_backend.config;

import com.example.local_image_controller_final_project_backend.model.ERole;
import com.example.local_image_controller_final_project_backend.model.UserModel;
import com.example.local_image_controller_final_project_backend.service.RoleModelService;
import com.example.local_image_controller_final_project_backend.service.UserModelDetailsService;
import com.example.local_image_controller_final_project_backend.service.UserModelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class RolesAndUsersToDBLoader implements CommandLineRunner {

    private final RoleModelService roleModelService;
    private final UserModelService userModelService;
    private final PasswordEncoder passwordEncoder;

    public RolesAndUsersToDBLoader(RoleModelService roleModelService, UserModelService userModelService, PasswordEncoder passwordEncoder) {
        this.roleModelService = roleModelService;
        this.userModelService = userModelService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        List<ERole> eRoleList = new LinkedList<>(Arrays.asList(ERole.ROLE_USER, ERole.ROLE_ADMIN, ERole.ROLE_MODERATOR));
        eRoleList.forEach(roleModelService::saveRoleToDB);

        UserModel admin = new UserModel("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN");
        UserModel user = new UserModel("user", passwordEncoder.encode("user"), "ROLE_USER");

        userModelService.saveUserToDB(admin);
        userModelService.saveUserToDB(user);

    }
}
