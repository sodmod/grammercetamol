package com.grammercetamol.mail.mailtoken_confirmation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Email_ConfirmationUtils {

    @Id
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long id;
    @Column(
            nullable = false,
            name = "token"
    )
    private String token;
    @Column(
            name = "mail",
            nullable = false
    )
    private String email;

    @Column(
            nullable = false,
            name = "created"
    )
    private LocalDateTime createdAt;

    @Column(
            nullable = false,
            name = "expired"
    )
    private LocalDateTime expiredAt;

    @Column(
            name = "confirmed"
    )
    private LocalDateTime confirmedAt;

    public Email_ConfirmationUtils(String token, String email, LocalDateTime createdAt, LocalDateTime expiredAt){
        this.token = token;
        this.email = email;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }
}
