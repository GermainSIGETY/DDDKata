package fr.sauceDallas.getThingsDone.todos.domain;

import fr.sauceDallas.getThingsDone.todos.presentation.ReadTodoResponse;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoUpdateRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class Todo {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime creationDatTime;

    private LocalDateTime dueDateTime;

    public Todo(String title, String description, Long dueDateTimeStamp) {
        this.title = title;
        this.description = description;
        this.creationDatTime = LocalDateTime.now();
        this.dueDateTime = timeStampToLocalDate(dueDateTimeStamp);
    }

    public Todo(Long id, String title, String description, LocalDateTime creationDatTime, LocalDateTime dueDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDatTime = creationDatTime;
        this.dueDateTime = dueDateTime;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDatTime() {
        return creationDatTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public ReadTodoResponse buildTodoResponse() {
        return new ReadTodoResponse(title, description, creationDatTime, dueDateTime);
    }

    public Todo updateFromUpdateRequest(TodoUpdateRequest request) {
        this.title=request.title;
        this.description= request.description;
        this.dueDateTime = timeStampToLocalDate(request.dueDateTimeStamp);
        return this;
    }

    private LocalDateTime timeStampToLocalDate(Long dueDateTimeStamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dueDateTimeStamp), TimeZone
                .getDefault().toZoneId());
    }
}
