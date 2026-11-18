package com.plazoleta.application.port.output;

/** Lo que el negocio necesita de afuera: alguien que sepa enviar un SMS (Twilio, SNS, etc.). */
public interface SmsSenderPort {

    void send(String celular, String mensaje);
}
