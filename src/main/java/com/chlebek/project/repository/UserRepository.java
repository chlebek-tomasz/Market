package com.chlebek.project.repository;

import com.chlebek.project.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByemail(String email);

    List<User> findByEmail(String email);
}
