package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.model.ERole;
import com.example.local_image_controller_final_project_backend.model.RoleModel;
import com.example.local_image_controller_final_project_backend.repository.RoleModelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleModelService {
    private final RoleModelRepository roleModelRepository;

    public RoleModelService(RoleModelRepository roleModelRepository) {
        this.roleModelRepository = roleModelRepository;
    }

    public Optional<RoleModel> findRoleByName(ERole roleName) {
        return roleModelRepository.findByName(roleName);
    }

    public void saveRoleToDB(ERole eRole){
        RoleModel roleModel = new RoleModel(eRole);
        roleModelRepository.save(roleModel);
    }
}
