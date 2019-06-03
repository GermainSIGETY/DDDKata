package fr.sauceDallas.getThingsDone.todos.infra.repository;

import fr.sauceDallas.getThingsDone.common.events.TodoUpdatedEvent;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoUpdatedEventHibernate;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoUpdatedEventHibernateMapperTest {

    public static final long ID = 22L;
    public static final String TITLE = "Moi je teste moi";
    public static final String ASSIGNEE = "please delegate";
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2015, Month.NOVEMBER,
            6,00,00,00);


    @Test
    public void fromTodoUpdatedEventHibernateTest() {
        TodoUpdatedEventHibernate eventHibernate = new TodoUpdatedEventHibernate();
        eventHibernate.id = ID;
        eventHibernate.title = TITLE;
        eventHibernate.assignee= ASSIGNEE;
        eventHibernate.occurredAt = OCCURRED_AT;
        TodoUpdatedEvent updatedEvent = TodoUpdatedEventHibernateMapper.fromEventHibernate(eventHibernate);

        assertThat(updatedEvent.getId()).isEqualTo(ID);
        assertThat(updatedEvent.getTitle()).isEqualTo(TITLE);
        assertThat(updatedEvent.getAssignee()).isEqualTo(ASSIGNEE);
        assertThat(updatedEvent.getOccurredAt()).isEqualTo(OCCURRED_AT);
    }

    @Test
    public void fromTodo() {
        TodoUpdatedEvent event = new TodoUpdatedEvent(ID, TITLE, ASSIGNEE, OCCURRED_AT);

        TodoUpdatedEventHibernate eventHibernate = TodoUpdatedEventHibernateMapper.fromTodoUpdatedEvent(event);

        assertThat(eventHibernate.id).isEqualTo(ID);
        assertThat(eventHibernate.title).isEqualTo(TITLE);
        assertThat(eventHibernate.assignee).isEqualTo(ASSIGNEE);
        assertThat(eventHibernate.occurredAt).isEqualTo(OCCURRED_AT);
    }
}
