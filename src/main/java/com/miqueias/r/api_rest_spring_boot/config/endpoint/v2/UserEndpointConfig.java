package com.miqueias.r.api_rest_spring_boot.config.endpoint.v2;


import com.miqueias.r.api_rest_spring_boot.exception.EndpointException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@Configuration("UserEndpointConfigV2")
public class UserEndpointConfig {

    @Value("${web.endpoint.v2.user.access.start.date:#{null}}")
    private String startDate;

    @Value("${web.endpoint.v2.user.access.limit.date:#{null}}")
    private String limitDate;

    private LocalDateTime getStartDate() {
        if (startDate == null || startDate.isEmpty()) {
            return null;
        }

        try {
            return LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato inválido de data de início: " + startDate, e);
        }
    }

    private LocalDateTime getLimitDate() {
        if (limitDate == null || limitDate.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(limitDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato inválido de data limite: " + limitDate, e);
        }
    }

    public String getDetailMessage() {
        LocalDateTime limitDate = this.getLimitDate();
        LocalDateTime now = LocalDateTime.now();

        if (limitDate != null) {
            long daysLeft = ChronoUnit.DAYS.between(now, limitDate);
            return "Este endpoint será desativado em "
                    + daysLeft + " dias.";
        }

        return "Este endpoint está atualmente ativo.";
    }

    public void checkEndpointAccess() {
        LocalDateTime startDate = this.getStartDate();
        LocalDateTime limitDate = this.getLimitDate();
        LocalDateTime now = LocalDateTime.now();

        if (startDate != null && now.isBefore(startDate)) {
            long daysUntilActive = ChronoUnit.DAYS.between(now, startDate);
            if (daysUntilActive == 0) {
                long hoursUntilActive = ChronoUnit.HOURS.between(now, startDate);
                throw new EndpointException("Este endpoint ainda não está disponível. Faltam " + hoursUntilActive + " horas para ser ativado.");
            } else {
                throw new EndpointException("Este endpoint ainda não está disponível. Faltam " + daysUntilActive + " dias para ser ativado.");
            }
        }

        if (limitDate != null && now.isAfter(limitDate)) {
            long daysSinceDeactivated = ChronoUnit.DAYS.between(limitDate, now);

            if (daysSinceDeactivated == 0) {
                long hoursSinceDeactivated = ChronoUnit.HOURS.between(limitDate, now);
                throw new EndpointException("Este endpoint não está mais acessível. Já se passaram " + hoursSinceDeactivated + " horas desde que foi desativado.");
            } else {
                throw new EndpointException("Este endpoint não está mais acessível. Já se passaram " + daysSinceDeactivated + " dias desde que foi desativado.");
            }
        }
    }



}
