package com.example.local_image_controller_final_project_backend.config;

import com.example.local_image_controller_final_project_backend.model.ERole;
import com.example.local_image_controller_final_project_backend.service.RoleModelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class LoadRolesOnStartup implements CommandLineRunner {

    private final RoleModelService roleModelService;

    public LoadRolesOnStartup(RoleModelService roleModelService) {
        this.roleModelService = roleModelService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<ERole> eRoleList = new LinkedList<>(Arrays.asList(ERole.ROLE_USER, ERole.ROLE_ADMIN, ERole.ROLE_MODERATOR));
        eRoleList.forEach(roleModelService::saveRoleToDB);
    }
}
