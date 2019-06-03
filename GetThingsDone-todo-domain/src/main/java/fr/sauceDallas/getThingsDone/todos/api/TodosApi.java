package fr.sauceDallas.getThingsDone.todos.api;

import fr.sauceDallas.getThingsDone.common.events.TodoUpdatedEvent;
import fr.sauceDallas.getThingsDone.common.infrastructure.TodosUpdatedEventRepository;
import fr.sauceDallas.getThingsDone.todos.domain.Todo;
import fr.sauceDallas.getThingsDone.todos.domain.validators.ValidationError;
import fr.sauceDallas.getThingsDone.todos.infrastructure.TodosRepository;
import fr.sauceDallas.getThingsDone.todos.presentation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

public class TodosApi {

    private TodosRepository todosRepository;

    private TodosUpdatedEventRepository todosUpdatedEventRepository;

    public TodosApi(TodosRepository todosRepository, TodosUpdatedEventRepository todosUpdatedEventRepository) {
        this.todosRepository = todosRepository;
        this.todosUpdatedEventRepository = todosUpdatedEventRepository;
    }

    @Transactional
    public ReadTodosResponse readTodos() {
        return new ReadTodosResponse(todosRepository.readTodos());
    }

    @Transactional
    public Long createTodo(TodoCreationRequest request) {
        Todo todo = new Todo(request.title, request.description, request.assignee, request.dueDateTimeStamp);
        this.todosUpdatedEventRepository.create(new TodoUpdatedEvent(request.title,request.assignee));
        return this.todosRepository.create(todo);
    }

    @Transactional
    public Optional<ReadTodoResponse> readTodo(Long todoId) {
        return todosRepository.readTodo(todoId).map(Todo::buildTodoResponse);
    }

    @Transactional
    public void updateTodo(TodoUpdateRequest request) throws TodoDomainException {

        Optional<Todo> todoOpt = todosRepository.readTodo(request.id);

        TodoUpdatedEvent todoUpdatedEvent = todoOpt.map(t -> t.updateFromUpdateRequest(request)).orElseThrow(() ->
                new TodoDomainException(Collections.
                        singletonList(new TodoError(ValidationError.INVALID_TODO_NUMBER.code, "id", ValidationError.INVALID_TODO_NUMBER.message))));

        this.todosRepository.update(todoOpt.get());
        this.todosUpdatedEventRepository.create(todoUpdatedEvent);
    }

    public void deleteTodo(Long id) throws TodoDomainException {
        Optional<Todo> todoOpt = todosRepository.readTodo(id);
        todoOpt.map((th) -> {
            todosRepository.delete(id);
            return id;
        }).orElseThrow(() ->
                new TodoDomainException(Collections.
                        singletonList(new TodoError(ValidationError.INVALID_TODO_NUMBER.code, "id", ValidationError.INVALID_TODO_NUMBER.message))));
    }
}
