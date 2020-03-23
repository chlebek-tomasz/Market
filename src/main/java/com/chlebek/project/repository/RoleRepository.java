package com.chlebek.project.repository;

import com.chlebek.project.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleById(Long id);
}
