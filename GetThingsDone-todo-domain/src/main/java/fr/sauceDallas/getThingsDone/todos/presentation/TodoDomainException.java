package fr.sauceDallas.getThingsDone.todos.presentation;

import java.util.List;

public class TodoDomainException extends Exception {

    public final List<TodoError> errors;

    public TodoDomainException(List<TodoError> errors) {
        this.errors=errors;
    }
}
