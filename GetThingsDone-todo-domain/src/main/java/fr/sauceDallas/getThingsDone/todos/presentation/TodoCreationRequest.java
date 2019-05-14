package fr.sauceDallas.getThingsDone.todos.presentation;

import fr.sauceDallas.getThingsDone.todos.domain.validators.DescriptionValidator;
import fr.sauceDallas.getThingsDone.todos.domain.validators.DueDateValidator;
import fr.sauceDallas.getThingsDone.todos.domain.validators.TitleValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoCreationRequest {
    
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DUE_DATE = "dueDate";

    public final String title;

    public final String description;

    public final Long dueDateTimeStamp;

    private TodoCreationRequest(String title, String description, Long dueDateTimeStamp) {
        this.title = title;
        this.description = description;
        this.dueDateTimeStamp = dueDateTimeStamp;
    }

    public static TodoCreationRequest createTodoCreationRequest(String title, String description, Long dueDatetimetimeStamp) throws TodoDomainException {
        validate(title, description, dueDatetimetimeStamp);
        return new TodoCreationRequest(title, description, dueDatetimetimeStamp);
    }

    private static void validate(String title, String description, Long dueDateTimeStamp) throws TodoDomainException {
        List<TodoError> errors = new ArrayList<>();

        errors.addAll(TitleValidator.validate(title).
                stream().map(vr -> new TodoError(vr.code, TITLE, vr.message)).collect(Collectors.toList()));
        errors.addAll(DescriptionValidator.validate(description).
                stream().map(vr -> new TodoError(vr.code, DESCRIPTION, vr.message)).collect(Collectors.toList()));
        errors.addAll(DueDateValidator.validate(dueDateTimeStamp).
                stream().map(vr -> new TodoError(vr.code, DUE_DATE, vr.message)).collect(Collectors.toList()));

        if (!errors.isEmpty()) {
            throw new TodoDomainException(errors);
        }
    }
}
