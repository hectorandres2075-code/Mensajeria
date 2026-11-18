package com.plazoleta.infrastructure.input.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SmsRequest {

    @NotBlank(message = "El celular es obligatorio")
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "El celular debe tener máximo 13 caracteres y puede iniciar con +")
    private String celular;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 480, message = "El mensaje no puede pasar de 480 caracteres")
    private String mensaje;

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
