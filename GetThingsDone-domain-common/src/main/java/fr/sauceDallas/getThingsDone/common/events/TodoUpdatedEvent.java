package fr.sauceDallas.getThingsDone.common.events;

import java.time.LocalDateTime;

public class TodoUpdatedEvent {

    private Long id;

    private String title;

    private String assignee;

    private LocalDateTime occurredAt;

    private Boolean processed;

    public TodoUpdatedEvent(String title, String assignee) {
        this.title = title;
        this.assignee = assignee;
        this.occurredAt = LocalDateTime.now();
        this.processed = false;
    }

    public TodoUpdatedEvent(Long id, String title, String assignee, LocalDateTime occurredAt, Boolean processed) {
        this.id= id;
        this.title = title;
        this.assignee = assignee;
        this.occurredAt = occurredAt;
        this.processed = processed;
    }

    public void markAsProcessed() {
        this.processed = true;
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

    public Boolean getProcessed() {
        return processed;
    }
}
