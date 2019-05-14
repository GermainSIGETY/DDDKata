package fr.sauceDallas.getThingsDone.todos.presentation;

import java.util.List;

public class ReadTodosResponse {

    public final List<TodoSummary> todos;

    public ReadTodosResponse(List<TodoSummary> todos) {
        this.todos = todos;
    }
}
