package fr.sauceDallas.getThingsDone.app.ui.rest.exceptions;

import fr.sauceDallas.getThingsDone.todos.presentation.TodoError;

import java.util.ArrayList;
import java.util.List;

public class ErrorsJSONPayload {
    
    public final List<ErrorJSON> errors;

    public ErrorsJSONPayload(List<TodoError> applicationErrors) {
        this.errors = new ArrayList<>();
        applicationErrors.forEach(applicationError -> errors.add(fromApplicationError(applicationError)));
    }

    private ErrorJSON fromApplicationError(TodoError applicationError) {
        return new ErrorJSON(applicationError.code, applicationError.field, applicationError.description);
    }
}
