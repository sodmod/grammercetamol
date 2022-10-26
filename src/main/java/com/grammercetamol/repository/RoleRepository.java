package com.grammercetamol.repository;

import com.grammercetamol.models.ERole;
import com.grammercetamol.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
