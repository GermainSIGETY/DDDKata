package fr.sauceDallas.getThingsDone.app.ui.rest.update;

import fr.sauceDallas.getThingsDone.todos.api.TodosApi;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoDomainException;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/todos")
public class TodoUpdateController {

    private TodosApi todosApi;

    public TodoUpdateController(TodosApi todosApi) {
        this.todosApi = todosApi;
    }

    @RequestMapping(value = "{todoId}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Void> updateTodo(@PathVariable(value = "todoId") Long todoId,
                                           @RequestBody TodoUpdateJSONRequest request) throws TodoDomainException {
        todosApi.updateTodo(TodoUpdateRequest.factory(todoId, request.title, request.description, request.dueDateTimeStamp));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
