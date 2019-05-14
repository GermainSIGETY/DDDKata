package fr.sauceDallas.getThingsDone.app.ui.rest.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorJSON {

    public final String code;
    public final String field;
    public final String message;
    
    public ErrorJSON(String code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }
}
