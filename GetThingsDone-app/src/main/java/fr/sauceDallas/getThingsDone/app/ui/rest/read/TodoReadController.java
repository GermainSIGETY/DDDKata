package fr.sauceDallas.getThingsDone.app.ui.rest.read;

import fr.sauceDallas.getThingsDone.todos.api.TodosApi;
import fr.sauceDallas.getThingsDone.app.ui.rest.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/todos")
public class TodoReadController {

    private TodosApi todosApi;

    public TodoReadController(TodosApi todosApi) {
        this.todosApi = todosApi;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReadTodosJSONResponse readTodos() {
        return new ReadTodosJSONResponse(todosApi.readTodos());
    }

    @RequestMapping(value = "{todoId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public TodoDetailJSONResponse readTodo(@PathVariable(value = "todoId") Long todoId) throws ResourceNotFoundException {
        return todosApi.readTodo(todoId)
                .map(t -> new TodoDetailJSONResponse(t.title, t.description, t.creationDatTime, t.dueDateTime))
                .orElseThrow(() -> new ResourceNotFoundException());
    }
}
