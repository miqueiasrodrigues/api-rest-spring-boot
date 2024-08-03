package com.miqueias.r.api_rest_spring_boot.config.endpoint.v1;

import com.miqueias.r.api_rest_spring_boot.exception.EndpointException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

@Configuration("UserEndpointConfigV1")
public class UserEndpointConfig {

    @Value("${web.endpoint.v1.user.access.start.date:#{null}}")
    private String startDate;

    @Value("${web.endpoint.v1.user.access.limit.date:#{null}}")
    private String limitDate;

    private LocalDateTime getStartDate() {
        if (startDate == null || startDate.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid start date format: " + startDate, e);
        }
    }

    private LocalDateTime getLimitDate() {
        if (limitDate == null || limitDate.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(limitDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid limit date format: " + limitDate, e);
        }
    }

    public String getDetailMessage() {
        LocalDateTime limitDate = this.getLimitDate();
        LocalDateTime now = LocalDateTime.now();

        if (limitDate != null) {
            long daysLeft = ChronoUnit.DAYS.between(now, limitDate);
            return "This Endpoint will be deactivated in "
                    + daysLeft + " days.";
        }

        return "This Endpoint is currently active.";
    }

    public void checkEndpointAccess() {
        LocalDateTime startDate = this.getStartDate();
        LocalDateTime limitDate = this.getLimitDate();
        LocalDateTime now = LocalDateTime.now();

        if (startDate != null && now.isBefore(startDate)) {
            long daysUntilActive = ChronoUnit.DAYS.between(now, startDate);
            if (daysUntilActive == 0) {
                long hoursUntilActive = ChronoUnit.HOURS.between(now, startDate);
                throw new EndpointException("This endpoint is not available yet. " + hoursUntilActive + " hours left until it becomes active.");
            } else {
                throw new EndpointException("This endpoint is not available yet. " + daysUntilActive + " days left until it becomes active.");
            }
        }

        if (limitDate != null && now.isAfter(limitDate)) {
            long daysSinceDeactivated = ChronoUnit.DAYS.between(limitDate, now);

            if (daysSinceDeactivated == 0) {
                long hoursSinceDeactivated = ChronoUnit.HOURS.between(limitDate, now);
                throw new EndpointException("This endpoint is no longer accessible. " + hoursSinceDeactivated + " hours have passed since it was deactivated.");
            } else {
                throw new EndpointException("This endpoint is no longer accessible. " + daysSinceDeactivated + " days have passed since it was deactivated.");
            }
        }
    }


}
