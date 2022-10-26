package com.grammercetamol.mail.mailtoken_confirmation;

import com.grammercetamol.mail.services.EmailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.grammercetamol.StaticStrings.StaticStrings.MAIL_CONFIRMATION;
import static com.grammercetamol.StaticStrings.StaticStrings.MESSAGE;

@Service
@Component
@Data
public class Email_ConfirmationService {

    @Autowired
    Email_ConfirmationRepo email_confirmationRepo;

    @Autowired
    EmailService emailService;
    @Value("${spring.mail.username}")
    private String sentFrom;



    public String SaveAndSendToken(@NotNull String email, @NotNull String firstname, @NotNull String lastname){

        String name = firstname.concat(" "+ lastname);

        String token = UUID.randomUUID().toString();

        Email_ConfirmationUtils email_confirmationUtils = new Email_ConfirmationUtils(
                token,
                email,
                LocalDateTime.now(),
                LocalDateTime.
                        now()
                        .plusHours(1)
        );

        String link = "http://localhost:8001/api/v1/registration/confirmToken?token=";

        emailService.send(email, MESSAGE(name, link,token), sentFrom , MAIL_CONFIRMATION);

        email_confirmationRepo.save(email_confirmationUtils);

        return null;
    }

    public String confirmToken(){
        return null;
    }


}
