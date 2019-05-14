package fr.sauceDallas.getThingsDone.todos.infrastructure;

import fr.sauceDallas.getThingsDone.todos.domain.Todo;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoSummary;

import java.util.List;
import java.util.Optional;

public interface TodosRepository {

    List<TodoSummary> readTodos();

    Optional<Todo> readTodo(Long id);

    Long create(Todo todo);

    void update(Todo todo);

    void delete(Long id);
}
