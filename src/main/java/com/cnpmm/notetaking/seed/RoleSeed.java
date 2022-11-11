//package com.cnpmm.notetaking.seed;
//
//import com.cnpmm.notetaking.model.Role;
//import com.cnpmm.notetaking.model.User;
//import com.cnpmm.notetaking.repository.RoleRepository;
//import com.cnpmm.notetaking.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class RoleSeed {
//    @Bean
//    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
//        return args -> {
//            Role userRole = new Role(null,"USER_ROLE");
//            Role adminRole = new Role(null, "ADMIN_ROLE");
//            roleRepository.saveAll(List.of(userRole,adminRole));
//        };
//    }
//}
