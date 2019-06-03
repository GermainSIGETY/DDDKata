package fr.sauceDallas.getThingsDone.todos.infra.repository;

import fr.sauceDallas.getThingsDone.common.events.TodoUpdatedEvent;
import fr.sauceDallas.getThingsDone.common.infrastructure.TodosUpdatedEventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RepositoriesConfiguration.class})
@ActiveProfiles({"test"})
@DataJpaTest
public class TodoUpdatedEventRepositoryTest {

    public static final String TITLE = "sortir le chien";
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2018, Month.JULY,
            2, 15, 47, 00);
    public static final String ASSIGNEE = "germs@germs.com";
    public static final String TITLE1 = "Finir le tricot";
    public static final String ASSIGNEE1 = "germs2@germs.com";

    @Autowired
    TodosUpdatedEventRepository repository;

    @Test
    public void testReadEvent() {

        Optional<TodoUpdatedEvent> event = repository.readEvent(1L);
        assertThat(event.isPresent()).isTrue();
        assertThat(event.get().getId()).isEqualTo(1L);
        assertThat(event.get().getTitle()).isEqualTo(TITLE);
        assertThat(event.get().getAssignee()).isEqualTo(ASSIGNEE);
        assertThat(event.get().getOccurredAt()).isEqualTo(OCCURRED_AT);
        assertThat(event.get().getProcessed()).isTrue();

    }

    @Test
    public void testReadEventReturnNothing() {
        Optional<TodoUpdatedEvent> event = repository.readEvent(1445657L);
        assertThat(event.isPresent()).isFalse();
    }


    @Test
    public void testCreate() {
        TodoUpdatedEvent event = new TodoUpdatedEvent(TITLE1, ASSIGNEE1);

        Long createdId = repository.create(event);

        assertThat(createdId).isNotNull();

        Optional<TodoUpdatedEvent> read = repository.readEvent(createdId);

        assertThat(read.isPresent()).isTrue();
        assertThat(read.get().getId()).isEqualTo(createdId);
        assertThat(read.get().getTitle()).isEqualTo(TITLE1);
        assertThat(read.get().getAssignee()).isEqualTo(ASSIGNEE1);
        assertThat(read.get().getOccurredAt()).isNotNull();
    }

    @Test
    public void testUpdate() {

        TodoUpdatedEvent event = new TodoUpdatedEvent(TITLE1, ASSIGNEE1);
        assertThat(event.getProcessed()).isFalse();
        Long createdId = repository.create(event);

        TodoUpdatedEvent readEvent = repository.readEvent(createdId).get();
        readEvent.markAsProcessed();
        repository.update(readEvent);

        Optional<TodoUpdatedEvent> read = repository.readEvent(createdId);
        assertThat(read.get().getProcessed()).isTrue();
    }

    @Test
    public void testGetEventsIdsToProcess () {
        List<Long> eventsIdsToProcess = repository.getEventsIdsToProcess();
        assertThat(eventsIdsToProcess.size()).isEqualTo(1);
        assertThat(eventsIdsToProcess.get(0)).isEqualTo(2);
    }
}
