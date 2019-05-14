package fr.sauceDallas.getThingsDone.todos.infra.repository;

import fr.sauceDallas.getThingsDone.todos.domain.Todo;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoHibernate;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoHibernateMapperTest {


    public static final long ID = 12L;
    public static final String TITLE = "Moi je teste moi";
    public static final String DESCRIPTION = "je fais des TUs pour tout dans la vie";
    private static final LocalDateTime CREATION_DATE = LocalDateTime.of(2015, Month.NOVEMBER,
            6,00,00,00);
    private static final LocalDateTime DUE_DATE = LocalDateTime.of(2015, Month.NOVEMBER,
            7,00,00,00);

    @Test
    public void fromTodoHibernateTest() {
        TodoHibernate todoHibernate = new TodoHibernate();
        todoHibernate.id = ID;
        todoHibernate.title = TITLE;
        todoHibernate.description = DESCRIPTION;
        todoHibernate.creationDateTime=CREATION_DATE;
        todoHibernate.dueDateTime =DUE_DATE;

        Todo todo = TodoHibernateMapper.fromTodoHibernate(todoHibernate);

        assertThat(todo.getId()).isEqualTo(ID);
        assertThat(todo.getTitle()).isEqualTo(TITLE);
        assertThat(todo.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(todo.getCreationDatTime()).isEqualTo(CREATION_DATE);
        assertThat(todo.getDueDateTime()).isEqualTo(DUE_DATE);
    }

    @Test
    public void fromTodo() {
        Todo todo = new Todo(ID,TITLE,DESCRIPTION,CREATION_DATE,DUE_DATE);

        TodoHibernate todoHibernate = TodoHibernateMapper.fromTodo(todo);

        assertThat(todoHibernate.id).isEqualTo(ID);
        assertThat(todoHibernate.title).isEqualTo(TITLE);
        assertThat(todoHibernate.description).isEqualTo(DESCRIPTION);
        assertThat(todoHibernate.creationDateTime).isEqualTo(CREATION_DATE);
        assertThat(todoHibernate.dueDateTime).isEqualTo(DUE_DATE);
    }

    @Test
    public void mapFields() {
        Todo todo = new Todo(ID,TITLE,DESCRIPTION,CREATION_DATE,DUE_DATE);

        TodoHibernate todoHibernate = new TodoHibernate();

        TodoHibernateMapper.mapHibernateFields(todo, todoHibernate);
        assertThat(todoHibernate.id).isEqualTo(ID);
        assertThat(todoHibernate.title).isEqualTo(TITLE);
        assertThat(todoHibernate.description).isEqualTo(DESCRIPTION);
        assertThat(todoHibernate.creationDateTime).isEqualTo(CREATION_DATE);
        assertThat(todoHibernate.dueDateTime).isEqualTo(DUE_DATE);
    }
}
