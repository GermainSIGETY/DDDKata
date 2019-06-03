package fr.sauceDallas.getThingsDone.todos.presentation;

import fr.sauceDallas.getThingsDone.todos.domain.validators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoUpdateRequest {
    
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String ASSIGNEE = "assignee";
    public static final String DUE_DATE = "dueDate";
    public static final String ID = "id";

    public final Long id;

    public final String title;

    public final String description;

    public final String assignee;

    public final Long dueDateTimeStamp;

    public TodoUpdateRequest(Long id, String title, String description, String assignee, Long dueDateTimeStamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.dueDateTimeStamp = dueDateTimeStamp;
    }

    public static TodoUpdateRequest factory(Long id, String title, String description, String assignee, Long dueDateTimeStamp) throws TodoDomainException {
        validate(id, title, description, assignee, dueDateTimeStamp);
        return new TodoUpdateRequest(id, title, description, assignee, dueDateTimeStamp);
    }

    private static void validate(Long id, String title, String description, String assignee, Long dueDateTimeStamp) throws TodoDomainException {
        List<TodoError> errors = new ArrayList<>();

        errors.addAll(TodoIdValidator.validate(id).
                stream().map(vr -> new TodoError(vr.code, ID, vr.message)).collect(Collectors.toList()));

        errors.addAll(TodoFieldsValidator.validate(title,description,assignee,dueDateTimeStamp));


        if (!errors.isEmpty()) {
            throw new TodoDomainException(errors);
        }
    }
}
