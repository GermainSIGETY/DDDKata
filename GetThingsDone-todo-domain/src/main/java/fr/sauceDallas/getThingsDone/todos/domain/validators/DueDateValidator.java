package fr.sauceDallas.getThingsDone.todos.domain.validators;

import java.util.Collections;
import java.util.List;

public class DueDateValidator {
    public static List<ValidationError> validate(Long dueDate) {
        if (dueDate == null) {
            return Collections.singletonList(ValidationError.EMPTY_FIELD);
        } if (dueDate < 0) {
            return Collections.singletonList(ValidationError.INVALID_NUMBER);
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
