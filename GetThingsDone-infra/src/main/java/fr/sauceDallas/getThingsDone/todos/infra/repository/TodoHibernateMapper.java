package fr.sauceDallas.getThingsDone.todos.infra.repository;

import fr.sauceDallas.getThingsDone.todos.domain.Todo;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoHibernate;

public class TodoHibernateMapper {


    public static Todo fromTodoHibernate(TodoHibernate todoHibernate) {
        return new Todo(todoHibernate.id,
                todoHibernate.title,
                todoHibernate.description,
                todoHibernate.creationDateTime,
                todoHibernate.dueDateTime);
    }

    public static TodoHibernate fromTodo(Todo todo) {
        TodoHibernate todoHibernate = new TodoHibernate();
        mapHibernateFields(todo, todoHibernate);
        return todoHibernate;
    }

    public static void mapHibernateFields(Todo todo, TodoHibernate todoHibernate) {
        todoHibernate.id = todo.getId();
        todoHibernate.title = todo.getTitle();
        todoHibernate.description = todo.getDescription();
        todoHibernate.creationDateTime = todo.getCreationDatTime();
        todoHibernate.dueDateTime = todo.getDueDateTime();
    }
}
