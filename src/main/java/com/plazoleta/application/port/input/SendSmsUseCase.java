package com.plazoleta.application.port.input;

public interface SendSmsUseCase {

    void sendSms(String celular, String mensaje);
}
