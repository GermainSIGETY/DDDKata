package fr.sauceDallas.getThingsDone.todos.infrastructure;

public interface EventNotifier {

    void notifyTodoUpdated(Long todoId);
}
