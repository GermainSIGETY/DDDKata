package fr.sauceDallas.getThingsDone.todos.presentation;

public class TodoError {

    public final String code;
    public final String field;
    public final String description;

    public TodoError(String code, String field, String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }
}
