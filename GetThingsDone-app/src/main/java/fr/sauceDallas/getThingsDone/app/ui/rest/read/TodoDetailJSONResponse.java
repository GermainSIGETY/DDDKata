package fr.sauceDallas.getThingsDone.app.ui.rest.read;

import java.time.LocalDateTime;

public class TodoDetailJSONResponse {

    public final String title;

    public final String description;

    public final LocalDateTime creationDatTime;

    public final LocalDateTime dueDateTime;

    public TodoDetailJSONResponse(String title, String description, LocalDateTime creationDatTime, LocalDateTime dueDateTime) {
        this.title = title;
        this.description = description;
        this.creationDatTime = creationDatTime;
        this.dueDateTime = dueDateTime;
    }
}
