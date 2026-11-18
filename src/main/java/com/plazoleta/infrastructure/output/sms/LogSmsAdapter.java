package com.plazoleta.infrastructure.output.sms;

import com.plazoleta.application.port.output.SmsSenderPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** SMS simulado: lo escribe en la consola en vez de enviarlo. Sirve para desarrollo y para la sustentación. */
public class LogSmsAdapter implements SmsSenderPort {

    private static final Logger LOG = LoggerFactory.getLogger(LogSmsAdapter.class);

    @Override
    public void send(String celular, String mensaje) {
        LOG.info("[SMS SIMULADO] Para {}: {}", celular, mensaje);
    }
}
