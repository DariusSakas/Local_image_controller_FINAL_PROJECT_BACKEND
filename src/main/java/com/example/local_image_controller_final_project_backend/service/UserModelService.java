package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.model.PrivilegeModel;
import com.example.local_image_controller_final_project_backend.model.RoleModel;
import com.example.local_image_controller_final_project_backend.model.UserModel;
import com.example.local_image_controller_final_project_backend.repository.RoleModelRepository;
import com.example.local_image_controller_final_project_backend.repository.UserModelRepository;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserModelService implements UserDetailsService {

    private final UserModelRepository userRepository;
    private final MessageSource messageSource;
    private final RoleModelRepository roleModelRepository;

    public UserModelService(UserModelRepository userRepository, MessageSource messageSource, RoleModelRepository roleModelRepository) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.roleModelRepository = roleModelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserModel userModel = userRepository.findByName(userName);
        if (userModel == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userModel;
    }
    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<RoleModel> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    private List<String> getPrivileges(Collection<RoleModel> roles) {

        List<String> privileges = new ArrayList<>();
        List<PrivilegeModel> collection = new ArrayList<>();
        for (RoleModel role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (PrivilegeModel item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
}
