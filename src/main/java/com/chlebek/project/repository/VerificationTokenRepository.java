package com.chlebek.project.repository;

import com.chlebek.project.model.user.User;
import com.chlebek.project.model.user.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    List<VerificationToken> findByUser(User user);
    List<VerificationToken> findByToken(String token);
}
