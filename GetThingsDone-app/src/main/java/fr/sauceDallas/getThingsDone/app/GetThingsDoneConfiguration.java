package fr.sauceDallas.getThingsDone.app;

import fr.sauceDallas.getThingsDone.alerts.api.AlertsAPI;
import fr.sauceDallas.getThingsDone.alerts.infrastructure.EmailAlertSender;
import fr.sauceDallas.getThingsDone.common.infrastructure.TodosUpdatedEventRepository;
import fr.sauceDallas.getThingsDone.todos.api.TodosApi;
import fr.sauceDallas.getThingsDone.todos.infra.InfraConfiguration;
import fr.sauceDallas.getThingsDone.todos.infra.eventNotifications.TodoEventNotificationConsumer;
import fr.sauceDallas.getThingsDone.todos.infra.eventNotifications.TodoEventNotificationPublisherImpl;
import fr.sauceDallas.getThingsDone.todos.infrastructure.EventNotifier;
import fr.sauceDallas.getThingsDone.todos.infrastructure.TodosRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAsync
@EnableScheduling
@Import({InfraConfiguration.class})
public class GetThingsDoneConfiguration {

    @Bean
    EventNotifier eventNotifier(ApplicationEventPublisher applicationEventPublisher) {
        return new TodoEventNotificationPublisherImpl(applicationEventPublisher);
    }

    @Bean
    TodoEventNotificationConsumer todoEventNotificationConsumer(AlertsAPI alertsAPI) {
        return new TodoEventNotificationConsumer(alertsAPI);
    }

    @Bean
    TodosApi todosApi(TodosRepository repository,
                      TodosUpdatedEventRepository eventRepository,
                      EventNotifier eventNotifier) {
        return new TodosApi(repository, eventRepository,eventNotifier);
    }

    @Bean
    AlertsAPI alertsAPI(TodosUpdatedEventRepository eventRepository, EmailAlertSender emailAlertSender) {
        return new AlertsAPI(eventRepository,emailAlertSender);
    }

    @Bean
    TasksScheduler tasksScheduler(AlertsAPI alertsAPI) {
        return new TasksScheduler(alertsAPI);
    }
}
