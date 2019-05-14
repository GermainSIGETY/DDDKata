package fr.sauceDallas.getThingsDone.app.ui.rest.delete;

import fr.sauceDallas.getThingsDone.todos.api.TodosApi;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/todos")
public class TodoDeletionController {

    private TodosApi todosApi;

    public TodoDeletionController(TodosApi todosApi) {
        this.todosApi = todosApi;
    }

    @RequestMapping(value = "{todoId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Void> deleteTodo(@PathVariable(value = "todoId") Long todoId) throws TodoDomainException {
        todosApi.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
