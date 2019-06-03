package fr.sauceDallas.getThingsDone.todos.infrastructure;

import fr.sauceDallas.getThingsDone.todos.domain.events.TodoUpdatedEvent;

import java.util.Optional;

public interface TodosUpdatedEventRepository {

    Optional<TodoUpdatedEvent> readEvent(Long id);

    Long create(TodoUpdatedEvent todo);

}
