package com.example.local_image_controller_final_project_backend.config;

import com.example.local_image_controller_final_project_backend.model.PrivilegeModel;
import com.example.local_image_controller_final_project_backend.model.RoleModel;
import com.example.local_image_controller_final_project_backend.model.UserModel;
import com.example.local_image_controller_final_project_backend.repository.PrivilegeModelRepository;
import com.example.local_image_controller_final_project_backend.repository.RoleModelRepository;
import com.example.local_image_controller_final_project_backend.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class UserDataLoader implements ApplicationListener<ContextClosedEvent> {
    private boolean alreadySetup = false;

    private UserModelRepository userModelRepository;
    private RoleModelRepository roleModelRepository;
    private PrivilegeModelRepository privilegeModelRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserModelRepository getUserModelRepository() {
        return userModelRepository;
    }

    @Autowired
    public RoleModelRepository getRoleModelRepository() {
        return roleModelRepository;
    }

    @Autowired
    public PrivilegeModelRepository getPrivilegeModelRepository() {
        return privilegeModelRepository;
    }

    @Autowired
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }


    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        if (alreadySetup)
            return;
        PrivilegeModel readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        PrivilegeModel writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<PrivilegeModel> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));

        RoleModel adminRole = roleModelRepository.findByName("ROLE_ADMIN");
        UserModel user = new UserModel();
        user.setUsername("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setRoles(Collections.singletonList(adminRole));
        userModelRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    PrivilegeModel createPrivilegeIfNotFound(String name) {

        PrivilegeModel privilege = privilegeModelRepository.findByName(name);
        if (privilege == null) {
            privilege = new PrivilegeModel(name);
            privilegeModelRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    RoleModel createRoleIfNotFound(
            String name, Collection<PrivilegeModel> privileges) {

        RoleModel role = roleModelRepository.findByName(name);
        if (role == null) {
            role = new RoleModel(name);
            role.setPrivileges(privileges);
            roleModelRepository.save(role);
        }
        return role;
    }

}
