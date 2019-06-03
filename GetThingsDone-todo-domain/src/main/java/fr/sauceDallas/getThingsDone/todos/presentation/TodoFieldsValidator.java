package fr.sauceDallas.getThingsDone.todos.presentation;

import fr.sauceDallas.getThingsDone.todos.domain.validators.AssigneeValidator;
import fr.sauceDallas.getThingsDone.todos.domain.validators.DescriptionValidator;
import fr.sauceDallas.getThingsDone.todos.domain.validators.DueDateValidator;
import fr.sauceDallas.getThingsDone.todos.domain.validators.TitleValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoFieldsValidator
{

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String ASSIGNEE = "assignee";
    public static final String DUE_DATE = "dueDate";

    public static List<TodoError> validate(String title, String description, String assignee, Long dueDateTimeStamp) {
        List<TodoError> errors = new ArrayList<>();

        errors.addAll(TitleValidator.validate(title).
                stream().map(vr -> new TodoError(vr.code, TITLE, vr.message)).collect(Collectors.toList()));
        errors.addAll(DescriptionValidator.validate(description).
                stream().map(vr -> new TodoError(vr.code, DESCRIPTION, vr.message)).collect(Collectors.toList()));
        errors.addAll(AssigneeValidator.validate(assignee).
                stream().map(vr -> new TodoError(vr.code, ASSIGNEE, vr.message)).collect(Collectors.toList()));
        errors.addAll(DueDateValidator.validate(dueDateTimeStamp).
                stream().map(vr -> new TodoError(vr.code, DUE_DATE, vr.message)).collect(Collectors.toList()));

        return errors;
    }
}
