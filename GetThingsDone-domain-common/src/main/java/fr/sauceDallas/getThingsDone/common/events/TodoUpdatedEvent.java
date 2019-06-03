package fr.sauceDallas.getThingsDone.common.events;

import java.time.LocalDateTime;

public class TodoUpdatedEvent {

    private Long id;

    private String title;

    private String assignee;

    private LocalDateTime occurredAt;

    public TodoUpdatedEvent(String title, String assignee) {
        this.title = title;
        this.assignee = assignee;
        this.occurredAt = LocalDateTime.now();
    }

    public TodoUpdatedEvent(Long id, String title, String assignee, LocalDateTime occurredAt) {
        this.id= id;
        this.title = title;
        this.assignee = assignee;
        this.occurredAt = occurredAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignee() {
        return assignee;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
