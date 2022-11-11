//package com.cnpmm.notetaking.seed;
//
//import com.cnpmm.notetaking.model.User;
//import com.cnpmm.notetaking.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class UserSeed {
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository) {
//        return args -> {
//            User tinnguyen = new User(
//                    "tinnguyen2682k1@gmail.com",
//                    "nguyenmaihoang742001"
//            );
//            User maihoang = new User(
//                    "pehoki2k8@gmail.com",
//                    "pehokicutee"
//            );
//            userRepository.saveAll(List.of(tinnguyen,maihoang));
//        };
//    }
//}
