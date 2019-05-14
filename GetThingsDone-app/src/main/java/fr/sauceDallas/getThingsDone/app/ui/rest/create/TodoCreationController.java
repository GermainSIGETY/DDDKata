package fr.sauceDallas.getThingsDone.app.ui.rest.create;

import fr.sauceDallas.getThingsDone.todos.api.TodosApi;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoCreationRequest;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoDomainException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/todos")
public class TodoCreationController {

    private TodosApi todosApi;

    public TodoCreationController(TodosApi todosApi) {
        this.todosApi = todosApi;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public TodoCreationJSONResponse CreateTodo(@RequestBody TodoCreationJSONRequest request) throws TodoDomainException {
        Long id = todosApi.createTodo(TodoCreationRequest.
                createTodoCreationRequest(request.title, request.description, request.dueDateTimeStamp));
        return new TodoCreationJSONResponse(id);
    }
}
