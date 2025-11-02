package com.tinkuytech.nango.message.exception;

public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(Long id) {
        super("Mensaje no encontrado con ID: " + id);
    }
}
