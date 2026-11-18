package com.plazoleta.infrastructure.configuration;

import com.plazoleta.application.port.input.SendSmsUseCase;
import com.plazoleta.application.port.output.SmsSenderPort;
import com.plazoleta.application.service.SendSmsService;
import com.plazoleta.infrastructure.output.sms.LogSmsAdapter;
import com.plazoleta.infrastructure.output.sms.TwilioSmsAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    /** Elige el proveedor de SMS según la propiedad sms.provider ("log" o "twilio"). */
    @Bean
    public SmsSenderPort smsSenderPort(@Value("${sms.provider}") String provider,
                                       @Value("${twilio.account-sid}") String accountSid,
                                       @Value("${twilio.auth-token}") String authToken,
                                       @Value("${twilio.from-number}") String fromNumber) {
        if ("twilio".equalsIgnoreCase(provider)) {
            return new TwilioSmsAdapter(accountSid, authToken, fromNumber);
        }
        return new LogSmsAdapter();
    }

    @Bean
    public SendSmsUseCase sendSmsUseCase(SmsSenderPort smsSenderPort) {
        return new SendSmsService(smsSenderPort);
    }
}
