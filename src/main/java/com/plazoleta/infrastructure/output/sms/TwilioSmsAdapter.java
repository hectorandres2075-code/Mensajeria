package com.plazoleta.infrastructure.output.sms;

import com.plazoleta.application.port.output.SmsSenderPort;
import com.plazoleta.domain.exception.SmsSendException;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

/** SMS real con la API REST de Twilio (sin SDK: es un POST con autenticación básica). */
public class TwilioSmsAdapter implements SmsSenderPort {

    private final RestClient restClient = RestClient.builder().baseUrl("https://api.twilio.com").build();
    private final String accountSid;
    private final String authToken;
    private final String fromNumber;

    public TwilioSmsAdapter(String accountSid, String authToken, String fromNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromNumber = fromNumber;
    }

    @Override
    public void send(String celular, String mensaje) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("To", celular);
        form.add("From", fromNumber);
        form.add("Body", mensaje);

        try {
            restClient.post()
                    .uri("/2010-04-01/Accounts/{sid}/Messages.json", accountSid)
                    .headers(h -> h.setBasicAuth(accountSid, authToken))
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(form)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientException e) {
            throw new SmsSendException();
        }
    }
}
