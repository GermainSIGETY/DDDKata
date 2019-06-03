package fr.sauceDallas.getThingsDone.todos.domain;

import fr.sauceDallas.getThingsDone.todos.domain.events.TodoUpdatedEvent;
import fr.sauceDallas.getThingsDone.todos.presentation.ReadTodoResponse;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoUpdateRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class Todo {

    private Long id;

    private String title;

    private String description;

    private String assignee;

    private LocalDateTime creationDatTime;

    private LocalDateTime dueDateTime;

    public Todo(String title, String description, String assignee, Long dueDateTimeStamp) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.creationDatTime = LocalDateTime.now();
        this.dueDateTime = timeStampToLocalDate(dueDateTimeStamp);
    }

    public Todo(Long id, String title, String description, String assignee, LocalDateTime creationDatTime, LocalDateTime dueDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignee = assignee;
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

    public String getAssignee() {
        return assignee;
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

    public TodoUpdatedEvent updateFromUpdateRequest(TodoUpdateRequest request) {
        this.title = request.title;
        this.description = request.description;
        this.assignee = request.assignee;
        this.dueDateTime = timeStampToLocalDate(request.dueDateTimeStamp);
        return new TodoUpdatedEvent(request.title, request.assignee);
    }

    private LocalDateTime timeStampToLocalDate(Long dueDateTimeStamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dueDateTimeStamp), TimeZone
                .getDefault().toZoneId());
    }
}
