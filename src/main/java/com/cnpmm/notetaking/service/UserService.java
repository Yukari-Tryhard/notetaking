package com.cnpmm.notetaking.service;

import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getListUser(){
        return userRepository.findAll();
    }

    public User addNewUser(User user) {
        User userByEmail = userRepository.
                findUserByEmail(user.getEmail());
        if (userByEmail != null){
            throw new IllegalStateException("email taken");
        }
        else{
            user.setPassword(passwordEncoder.encode((user.getPassword())));
            return userRepository.save(user);
        }
    }

    public void deleteUser(Integer id) {
        boolean exists = userRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException(
                    "user with id " + id + " does not exists"
            );
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(Integer id, String email, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("user with id "+id+" does not exists"));
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(), email)){
            user.setEmail(email);
        }

        if (password != null &&
                password.length() > 0 &&
                !Objects.equals(user.getPassword(), password)) {

            user.setPassword(passwordEncoder.encode(password));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }else{
            log.info("User found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }
}
