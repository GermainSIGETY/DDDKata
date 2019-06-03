package fr.sauceDallas.getThingsDone.alerts.infrastructure;

import java.time.LocalDateTime;

public interface EmailAlertSender {

    void sendEmail(String dest, String title, LocalDateTime date);
}
