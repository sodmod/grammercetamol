package com.grammercetamol.mailtoken_confirmation;

import com.grammercetamol.mail.EmailService;
import com.grammercetamol.models.AppUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.grammercetamol.StaticStrings.StaticStrings.*;

@Service
public class Email_ConfirmationService {

    @Autowired
    Email_ConfirmationRepo email_confirmationRepo;

    @Autowired
    EmailService emailService;
    @Value("${horluwatosin1999@gmail.com}")
    private String sentFrom;



    public String savetoken(@NotNull AppUsers appUsers){

        String sendTo = appUsers.getEmail();
        String name = appUsers.getFirstName().concat(" "+ appUsers.getLastName());

        String token = UUID.randomUUID().toString();

        Email_ConfirmationUtils email_confirmationUtils = new Email_ConfirmationUtils(
                token,
                appUsers.getEmail(),
                LocalDateTime.now(),
                LocalDateTime.
                        now()
                        .plusHours(1)
        );

        String link = "http://localhost:8001/api/v1/registration/confirmToken?token=";

        emailService.send(sendTo, MESSAGE(name, link,token), sentFrom , MAIL_CONFIRMATION);

        email_confirmationRepo
                .save(
                        email_confirmationUtils
                );
        return null;
    }


}
