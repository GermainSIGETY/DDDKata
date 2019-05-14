package fr.sauceDallas.getThingsDone.todos.domain.validators;

public enum ValidationError {

    EMPTY_FIELD("EMPTY_FIELD", "Veuillez saisir ce champ"),
    INVALID_NUMBER("INVALID_NUMBER", "format du nombre invalide"),
    INVALID_TODO_NUMBER("INVALID_TODO_NUMBER", "aucun TODO avec cet id");

    public final String code;
    public final String message;

    ValidationError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
