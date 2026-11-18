package com.plazoleta.application.service;

import com.plazoleta.application.port.output.SmsSenderPort;
import com.plazoleta.domain.exception.InvalidSmsException;
import com.plazoleta.domain.exception.SmsSendException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendSmsServiceTest {

    @Mock private SmsSenderPort smsSenderPort;

    private SendSmsService service;

    @BeforeEach
    void setUp() {
        service = new SendSmsService(smsSenderPort);
    }

    @Test
    void sendSms_datosValidos_loEnviaPorElPuerto() {
        service.sendSms("+573001234567", "Tu pedido está listo");

        verify(smsSenderPort).send("+573001234567", "Tu pedido está listo");
    }

    @Test
    void sendSms_celularVacio_lanzaExcepcion() {
        assertThrows(InvalidSmsException.class, () -> service.sendSms(" ", "hola"));
        verify(smsSenderPort, never()).send(anyString(), anyString());
    }

    @Test
    void sendSms_mensajeVacio_lanzaExcepcion() {
        assertThrows(InvalidSmsException.class, () -> service.sendSms("+573001234567", ""));
        verify(smsSenderPort, never()).send(anyString(), anyString());
    }

    @Test
    void sendSms_siElProveedorFalla_propagaLaExcepcion() {
        doThrow(new SmsSendException()).when(smsSenderPort).send("+573001234567", "hola");

        assertThrows(SmsSendException.class, () -> service.sendSms("+573001234567", "hola"));
    }
}
