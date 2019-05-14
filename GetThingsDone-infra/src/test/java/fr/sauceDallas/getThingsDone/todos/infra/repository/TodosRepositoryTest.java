package fr.sauceDallas.getThingsDone.todos.infra.repository;

import fr.sauceDallas.getThingsDone.todos.domain.Todo;
import fr.sauceDallas.getThingsDone.todos.infra.InfraConfiguration;
import fr.sauceDallas.getThingsDone.todos.infrastructure.TodosRepository;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoSummary;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoUpdateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {InfraConfiguration.class})
@ActiveProfiles({"test"})
@DataJpaTest
public class TodosRepositoryTest {

    public static final String TITLE = "sortir le chien";

    private static final LocalDateTime CREATION_DATE = LocalDateTime.of(2018, Month.JULY,
            2,15,47,00);
    private static final LocalDateTime DUE_DATE = LocalDateTime.of(2018, Month.JULY,
            2,16,47,00);
    public static final String DESCRIPTION = "le pauvre";
    public static final String TITLE1 = "Finir le tricot";
    public static final String DESCRIPTION1 = "devant Julien Lepers";


    @Autowired
    TodosRepository todosRepository;

    @Test
    public void testReadTodos() {
        List<TodoSummary> summaries = todosRepository.readTodos();
        assertThat(summaries).size().isEqualTo(2);
        TodoSummary one = summaries.get(0);
        assertThat(one.id).isEqualTo(1L);
        assertThat(one.title).isEqualTo(TITLE);
    }

    @Test
    public void testReadTodo() {

        Optional<Todo> todo = todosRepository.readTodo(1L);

        assertThat(todo.isPresent()).isTrue();
        assertThat(todo.get().getId()).isEqualTo(1L);
        assertThat(todo.get().getTitle()).isEqualTo(TITLE);
        assertThat(todo.get().getDescription()).isEqualTo(DESCRIPTION);
        assertThat(todo.get().getCreationDatTime()).isEqualTo(CREATION_DATE);
        assertThat(todo.get().getDueDateTime()).isEqualTo(DUE_DATE);
    }

    @Test
    public void testReadTodoReturnNothing() {
        Optional<Todo> todo = todosRepository.readTodo(1445657L);
        assertThat(todo.isPresent()).isFalse();
    }


    @Test
    public void testCreate() {
        Todo todo = new Todo(TITLE1, DESCRIPTION1, Timestamp.valueOf(DUE_DATE).getTime());

        Long createdId = todosRepository.create(todo);

        assertThat(createdId).isNotNull();

        Optional<Todo> read = todosRepository.readTodo(createdId);

        assertThat(read.isPresent()).isTrue();
        assertThat(read.get().getId()).isEqualTo(createdId);
        assertThat(read.get().getTitle()).isEqualTo(TITLE1);
        assertThat(read.get().getDescription()).isEqualTo(DESCRIPTION1);
        assertThat(read.get().getCreationDatTime()).isNotNull();
        assertThat(read.get().getDueDateTime()).isEqualTo(DUE_DATE);
    }

    @Test
    public void testUpdate() {

        Todo todo = todosRepository.readTodo(1L).get();
        todo.updateFromUpdateRequest(new TodoUpdateRequest(1L, TITLE1, DESCRIPTION1,Timestamp.valueOf(DUE_DATE).getTime()));

        todosRepository.update(todo);

        Optional<Todo> read = todosRepository.readTodo(1L);

        assertThat(read.isPresent()).isTrue();
        assertThat(read.get().getId()).isEqualTo(1L);
        assertThat(read.get().getTitle()).isEqualTo(TITLE1);
        assertThat(read.get().getDescription()).isEqualTo(DESCRIPTION1);
        assertThat(read.get().getCreationDatTime()).isNotNull();
        assertThat(read.get().getDueDateTime()).isEqualTo(DUE_DATE);
    }

    @Test
    public void testDelete() {
        Todo todo = new Todo(TITLE1, DESCRIPTION1, Timestamp.valueOf(DUE_DATE).getTime());

        Long createdId = todosRepository.create(todo);

        assertThat(createdId).isNotNull();

        Optional<Todo> read = todosRepository.readTodo(createdId);
        assertThat(read.isPresent()).isTrue();

        todosRepository.delete(createdId);

        Optional<Todo> readAfterDeletion = todosRepository.readTodo(createdId);
        assertThat(readAfterDeletion.isPresent()).isFalse();
    }

}
