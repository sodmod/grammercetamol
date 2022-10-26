package com.grammercetamol.repository;

import com.grammercetamol.models.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUsers, Long> {
    Optional<AppUsers> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
