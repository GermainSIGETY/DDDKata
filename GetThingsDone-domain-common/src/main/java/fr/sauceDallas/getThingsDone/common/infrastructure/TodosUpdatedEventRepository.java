package fr.sauceDallas.getThingsDone.common.infrastructure;

import fr.sauceDallas.getThingsDone.common.events.TodoUpdatedEvent;

import java.util.Optional;

public interface TodosUpdatedEventRepository {

    Optional<TodoUpdatedEvent> readEvent(Long id);

    Long create(TodoUpdatedEvent todo);

}
