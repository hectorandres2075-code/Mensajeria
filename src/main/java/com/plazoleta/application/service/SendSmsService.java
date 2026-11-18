package com.plazoleta.application.service;

import com.plazoleta.application.port.input.SendSmsUseCase;
import com.plazoleta.application.port.output.SmsSenderPort;
import com.plazoleta.domain.exception.InvalidSmsException;

public class SendSmsService implements SendSmsUseCase {

    private final SmsSenderPort smsSenderPort;

    public SendSmsService(SmsSenderPort smsSenderPort) {
        this.smsSenderPort = smsSenderPort;
    }

    @Override
    public void sendSms(String celular, String mensaje) {
        if (celular == null || celular.isBlank()) {
            throw new InvalidSmsException("El celular es obligatorio");
        }
        if (mensaje == null || mensaje.isBlank()) {
            throw new InvalidSmsException("El mensaje es obligatorio");
        }
        smsSenderPort.send(celular, mensaje);
    }
}
