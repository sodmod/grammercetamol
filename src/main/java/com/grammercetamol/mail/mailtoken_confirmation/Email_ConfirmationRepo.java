package com.grammercetamol.mail.mailtoken_confirmation;
/*
* In the Repo class, I am handling if confirmation of token where the token will b saved into a database
* which will be use to query the database if the info sent is correct or not i.e if the application is using otp approach
* */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Email_ConfirmationRepo extends JpaRepository<Email_ConfirmationUtils, Long> {

    Optional<Email_ConfirmationUtils> findByEmail(String email);
}
