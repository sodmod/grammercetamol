package com.grammercetamol.mail.services;

public interface EmailSender {

    void send(String to, String message, String from, String Subject);
}
