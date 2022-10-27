package com.grammercetamol.mail.mailtoken_confirmation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Email_ConfirmationRepo extends JpaRepository<Email_ConfirmationUtils, Long> {

    Optional<Email_ConfirmationUtils> findByEmail(String email);
}
