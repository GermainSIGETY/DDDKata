package fr.sauceDallas.getThingsDone.app.ui.rest.update;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoUpdateJSONRequest {

    public final String title;

    public final String description;

    public final Long dueDateTimeStamp;

    public TodoUpdateJSONRequest(@JsonProperty("title")String title,
                                 @JsonProperty("description")String description,
                                 @JsonProperty("dueDate")Long dueDate) {
        this.title = title;
        this.description=description;
        this.dueDateTimeStamp = dueDate;
    }
}
