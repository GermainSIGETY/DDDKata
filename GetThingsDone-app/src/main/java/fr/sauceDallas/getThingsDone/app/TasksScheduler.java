package fr.sauceDallas.getThingsDone.app;

import fr.sauceDallas.getThingsDone.alerts.api.AlertsAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class TasksScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TasksScheduler.class);

    private AlertsAPI alertsAPI;

    public TasksScheduler(AlertsAPI alertsAPI) {
        this.alertsAPI = alertsAPI;
    }

    @Scheduled(cron = "0 * * * * *")
    void resendAlerts() {
        LOGGER.info("reattempt to send alerts");
        List<Long> eventsIds = alertsAPI.retrieveAlertsIdsToResend();
        eventsIds.forEach(e -> alertsAPI.sendTodoUpdatedAlert(e));
    }
}
