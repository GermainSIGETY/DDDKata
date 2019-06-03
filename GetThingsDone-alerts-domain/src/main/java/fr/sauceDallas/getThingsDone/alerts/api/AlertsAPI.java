package fr.sauceDallas.getThingsDone.alerts.api;

import fr.sauceDallas.getThingsDone.alerts.infrastructure.EmailAlertSender;
import fr.sauceDallas.getThingsDone.common.events.TodoUpdatedEvent;
import fr.sauceDallas.getThingsDone.common.infrastructure.TodosUpdatedEventRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class AlertsAPI {

    private TodosUpdatedEventRepository todosUpdatedEventRepository;

    private EmailAlertSender emailAlertSender;

    public AlertsAPI(TodosUpdatedEventRepository todosUpdatedEventRepository, EmailAlertSender emailAlertSender) {
        this.todosUpdatedEventRepository = todosUpdatedEventRepository;
        this.emailAlertSender = emailAlertSender;
    }

    @Transactional
    public void sendTodoUpdatedAlert(Long todoUpdatedEventId) {
        Optional<TodoUpdatedEvent> optEvent = this.todosUpdatedEventRepository.readEvent(todoUpdatedEventId);
        optEvent.ifPresent(event -> handleAlertFromEvent(event));
    }

    private void handleAlertFromEvent(TodoUpdatedEvent event) {
        emailAlertSender.sendEmail(event.getAssignee(),event.getTitle(),event.getOccurredAt());
        event.markAsProcessed();
        this.todosUpdatedEventRepository.update(event);
    }

    @Transactional
    public List<Long> retrieveAlertsIdsToResend() {
        return todosUpdatedEventRepository.getEventsIdsToProcess();
    }
}
