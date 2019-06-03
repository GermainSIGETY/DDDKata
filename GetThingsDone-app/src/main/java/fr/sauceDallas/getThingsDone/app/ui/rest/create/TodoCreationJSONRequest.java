package fr.sauceDallas.getThingsDone.app.ui.rest.create;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoCreationJSONRequest {

    public final String title;

    public final String description;

    public final String assignee;

    public final Long dueDateTimeStamp;

    public TodoCreationJSONRequest(@JsonProperty("title")String title,
                                   @JsonProperty("description")String description,
                                   @JsonProperty("assignee")String assignee,
                                   @JsonProperty("dueDate")Long dueDate) {
        this.title = title;
        this.description=description;
        this.assignee=assignee;
        this.dueDateTimeStamp = dueDate;
    }
}
