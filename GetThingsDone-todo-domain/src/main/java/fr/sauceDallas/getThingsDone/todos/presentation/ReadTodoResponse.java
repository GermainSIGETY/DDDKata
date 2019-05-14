package fr.sauceDallas.getThingsDone.todos.presentation;

import java.time.LocalDateTime;

public class ReadTodoResponse {

    public final String title;

    public final String description;

    public final LocalDateTime creationDatTime;

    public final LocalDateTime dueDateTime;

    public ReadTodoResponse(String title, String description, LocalDateTime creationDatTime, LocalDateTime dueDateTime) {
        this.title = title;
        this.description = description;
        this.creationDatTime = creationDatTime;
        this.dueDateTime = dueDateTime;
    }
}
