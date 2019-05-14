package fr.sauceDallas.getThingsDone.app.ui.rest.read;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.sauceDallas.getThingsDone.todos.presentation.ReadTodosResponse;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReadTodosJSONResponse {

    public final List<TodoJSONResponse> todos;

    public ReadTodosJSONResponse(ReadTodosResponse readTodosResponse) {
        todos = readTodosResponse.todos.stream().map(t -> new TodoJSONResponse(t.id, t.title)).collect(Collectors.toList());
    }
}
