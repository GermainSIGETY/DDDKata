package fr.sauceDallas.getThingsDone.app.ui.rest.update;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoUpdateJSONRequest {

    public final String title;

    public final String description;

    public final String assignee;

    public final Long dueDateTimeStamp;

    public TodoUpdateJSONRequest(@JsonProperty("title")String title,
                                 @JsonProperty("description")String description,
                                 @JsonProperty("assignee")String assignee,
                                 @JsonProperty("dueDate")Long dueDate) {
        this.title = title;
        this.description=description;
        this.assignee = assignee;
        this.dueDateTimeStamp = dueDate;
    }
}
