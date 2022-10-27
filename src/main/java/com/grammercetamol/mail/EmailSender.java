package com.grammercetamol.mail;

public interface EmailSender {

    void send(String to, String message, String from, String Subject);
}
