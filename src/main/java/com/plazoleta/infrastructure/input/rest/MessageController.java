package com.plazoleta.infrastructure.input.rest;

import com.plazoleta.application.port.input.SendSmsUseCase;
import com.plazoleta.infrastructure.input.rest.dto.SmsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/messages")
@Tag(name = "Mensajería")
public class MessageController {

    private final SendSmsUseCase sendSmsUseCase;

    public MessageController(SendSmsUseCase sendSmsUseCase) {
        this.sendSmsUseCase = sendSmsUseCase;
    }

    @Operation(summary = "Enviar un SMS", description = "Solo el EMPLEADO (lo usa Plazoleta cuando un pedido queda listo)")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/sms")
    public ResponseEntity<Map<String, String>> sendSms(@Valid @RequestBody SmsRequest request) {
        sendSmsUseCase.sendSms(request.getCelular(), request.getMensaje());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of("estado", "ENVIADO"));
    }
}
