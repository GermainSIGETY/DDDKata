package fr.sauceDallas.getThingsDone.app.ui.rest.exceptions;

import fr.sauceDallas.getThingsDone.todos.presentation.TodoDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>("{\"errors\":[{\"code\":\"NOT_FOUND\"}]}", HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(TodoDomainException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<ErrorsJSONPayload> handleTodoDomainException(TodoDomainException e) {
        return new ResponseEntity<>(new ErrorsJSONPayload(e.errors),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
