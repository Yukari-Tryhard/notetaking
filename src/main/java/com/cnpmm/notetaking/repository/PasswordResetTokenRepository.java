package com.cnpmm.notetaking.repository;

import com.cnpmm.notetaking.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query(
            value = "select * from password_reset_token prt where prt.token = ?1",
            nativeQuery = true
    )
    PasswordResetToken findByToken(String token);
}
