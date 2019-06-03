package fr.sauceDallas.getThingsDone.todos.presentation;

import java.util.ArrayList;
import java.util.List;

public class TodoCreationRequest {


    public final String title;

    public final String description;

    public final String assignee;

    public final Long dueDateTimeStamp;

    private TodoCreationRequest(String title, String description, String assignee, Long dueDateTimeStamp) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.dueDateTimeStamp = dueDateTimeStamp;
    }

    public static TodoCreationRequest createTodoCreationRequest(String title, String description, String assignee, Long dueDatetimetimeStamp) throws TodoDomainException {
        validate(title, description, assignee, dueDatetimetimeStamp);
        return new TodoCreationRequest(title, description, assignee, dueDatetimetimeStamp);
    }

    private static void validate(String title, String description, String assignee, Long dueDateTimeStamp) throws TodoDomainException {
        List<TodoError> errors = new ArrayList<>();
        errors.addAll(TodoFieldsValidator.validate(title, description, assignee, dueDateTimeStamp));
        if (!errors.isEmpty()) {
            throw new TodoDomainException(errors);
        }
    }
}
