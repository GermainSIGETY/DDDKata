package fr.sauceDallas.getThingsDone.todos.infra.eventNotifications;

import fr.sauceDallas.getThingsDone.alerts.api.AlertsAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;

public class TodoEventNotificationConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoEventNotificationConsumer.class);

    private AlertsAPI alertsAPI;

    public TodoEventNotificationConsumer(AlertsAPI alertsAPI) {
        this.alertsAPI = alertsAPI;
    }

    @Async
    @TransactionalEventListener
    public void processTodoUpdatedEventNotification(long eventId) {
        LOGGER.info("handle TodoUpdatedEvent Notification");
        alertsAPI.sendTodoUpdatedAlert(eventId);

    }
}
