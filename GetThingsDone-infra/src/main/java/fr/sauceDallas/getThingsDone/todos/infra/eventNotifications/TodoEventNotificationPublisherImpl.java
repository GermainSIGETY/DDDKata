package fr.sauceDallas.getThingsDone.todos.infra.eventNotifications;

import fr.sauceDallas.getThingsDone.todos.infrastructure.EventNotifier;
import org.springframework.context.ApplicationEventPublisher;

public class TodoEventNotificationPublisherImpl implements EventNotifier {

    private ApplicationEventPublisher applicationEventPublisher;

    public TodoEventNotificationPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void notifyTodoUpdated(Long todoUpdatedEventId) {
        applicationEventPublisher.publishEvent(todoUpdatedEventId);
    }
}
