package com.cnpmm.notetaking.seed;

import com.cnpmm.notetaking.model.Role;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.RoleRepository;
import com.cnpmm.notetaking.repository.UserRepository;
import com.cnpmm.notetaking.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class Seeder {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserService userService) {
        return args -> {
            Role userRole = new Role(null,"USER_ROLE");
            Role adminRole = new Role(null, "ADMIN_ROLE");
            if (!roleRepository.findRoleByName(userRole.getName()).isPresent()){
                roleRepository.save(userRole);
            }
            if (!roleRepository.findRoleByName(adminRole.getName()).isPresent()){
                roleRepository.save(adminRole);
            }
            User tinnguyen = new User(
                    "tinnguyen2682k1@gmail.com",
                    "nguyenmaihoang742001"
            );
            User maihoang = new User(
                    "pehoki2k8@gmail.com",
                    "pehokicutee"
            );
            Collection<Role> onlyUserRole = new ArrayList<>();
            onlyUserRole.add(userRole);
            Collection<Role> onlyAdminRole = new ArrayList<>();
            onlyAdminRole.add(adminRole);
            tinnguyen.setRoles(onlyUserRole);
            maihoang.setRoles(onlyAdminRole);
            if (userService.findByEmail(tinnguyen.getEmail()) == null){
                userService.addNewUser(tinnguyen);
            }
            if (userService.findByEmail(maihoang.getEmail()) == null){
                userService.addNewUser(maihoang);
            }

        };
    }
}
