package com.grammercetamol.mail.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/*In this package, It is an independent package
* It handles sending of mail either with attachment or not... Attachment is yet to be implemented..
* As project is becoming big, attachment will be implementing it
* */
@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
    @Autowired
    JavaMailSender mailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);


    @Override
    @Async
    public void send(String to, String message, String from, String Subject) {
        try {
            MimeMessage mimeMailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, "utf-8");
            helper.setText(message, true);
            helper.setTo(to);
            helper.setSubject(Subject);
            mailSender.send(mimeMailMessage);
            LOGGER.info("mail send successfully");
        } catch (MessagingException e){
            LOGGER.error("failed to send email");
            throw new IllegalStateException("failed to send mail");
        }

    }
}
