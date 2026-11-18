package com.plazoleta.domain.exception;

public class SmsSendException extends RuntimeException {

    public SmsSendException() {
        super("No se pudo enviar el SMS");
    }
}
