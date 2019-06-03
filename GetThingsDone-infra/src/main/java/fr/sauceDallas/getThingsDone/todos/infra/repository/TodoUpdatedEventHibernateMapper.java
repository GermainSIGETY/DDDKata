package fr.sauceDallas.getThingsDone.todos.infra.repository;

import fr.sauceDallas.getThingsDone.common.events.TodoUpdatedEvent;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoUpdatedEventHibernate;

public class TodoUpdatedEventHibernateMapper {

    public static TodoUpdatedEvent fromEventHibernate(TodoUpdatedEventHibernate eventHibernate) {
        return new TodoUpdatedEvent(eventHibernate.id, eventHibernate.title,
                eventHibernate.assignee,
                eventHibernate.occurredAt);
    }

    public static TodoUpdatedEventHibernate fromTodoUpdatedEvent(TodoUpdatedEvent event) {
        TodoUpdatedEventHibernate eventHibernate = new TodoUpdatedEventHibernate();
        mapHibernateFields(event, eventHibernate);
        return eventHibernate;
    }

    public static void mapHibernateFields(TodoUpdatedEvent event, TodoUpdatedEventHibernate eventHibernate) {
        eventHibernate.id= event.getId();
        eventHibernate.title = event.getTitle();
        eventHibernate.assignee = event.getAssignee();
        eventHibernate.occurredAt = event.getOccurredAt();
    }
}
