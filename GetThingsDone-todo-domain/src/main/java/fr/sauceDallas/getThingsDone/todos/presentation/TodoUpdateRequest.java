package fr.sauceDallas.getThingsDone.todos.presentation;

import fr.sauceDallas.getThingsDone.todos.domain.validators.DescriptionValidator;
import fr.sauceDallas.getThingsDone.todos.domain.validators.DueDateValidator;
import fr.sauceDallas.getThingsDone.todos.domain.validators.TitleValidator;
import fr.sauceDallas.getThingsDone.todos.domain.validators.TodoIdValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoUpdateRequest {
    
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DUE_DATE = "dueDate";
    public static final String ID = "id";

    public final Long id;

    public final String title;

    public final String description;

    public final Long dueDateTimeStamp;

    public TodoUpdateRequest(Long id, String title, String description, Long dueDateTimeStamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDateTimeStamp = dueDateTimeStamp;
    }

    public static TodoUpdateRequest factory(Long id, String title, String description, Long dueDateTimeStamp) throws TodoDomainException {
        validate(id, title, description, dueDateTimeStamp);
        return new TodoUpdateRequest(id, title, description, dueDateTimeStamp);
    }

    private static void validate(Long id, String title, String description, Long dueDateTimeStamp) throws TodoDomainException {
        List<TodoError> errors = new ArrayList<>();

        errors.addAll(TodoIdValidator.validate(id).
                stream().map(vr -> new TodoError(vr.code, ID, vr.message)).collect(Collectors.toList()));
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
