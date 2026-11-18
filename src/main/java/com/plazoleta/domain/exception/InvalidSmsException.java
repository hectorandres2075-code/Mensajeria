package com.plazoleta.domain.exception;

public class InvalidSmsException extends RuntimeException {

    public InvalidSmsException(String message) {
        super(message);
    }
}
