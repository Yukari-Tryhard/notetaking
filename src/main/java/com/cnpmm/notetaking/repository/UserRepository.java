package com.cnpmm.notetaking.repository;

import com.cnpmm.notetaking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);

    @Query(value = "SELECT r.name FROM role r join user_roles ur on r.id = ur.roles_id WHERE ur.user_id = ?1", nativeQuery = true)
    Collection<String> getRolesString(Integer userId);
}
