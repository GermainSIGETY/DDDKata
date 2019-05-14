package fr.sauceDallas.getThingsDone.todos.infra.repository;

import fr.sauceDallas.getThingsDone.todos.domain.Todo;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoHibernate;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodosHibernateRepository;
import fr.sauceDallas.getThingsDone.todos.infrastructure.TodosRepository;
import fr.sauceDallas.getThingsDone.todos.presentation.TodoSummary;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodosRepositoryImpl implements TodosRepository {

    private TodosHibernateRepository todosHibernateRepository;

    public TodosRepositoryImpl(TodosHibernateRepository todosHibernateRepository) {
        this.todosHibernateRepository = todosHibernateRepository;
    }

    @Override
    public List<TodoSummary> readTodos() {

        List<TodoHibernate> todosHibernate = (List<TodoHibernate>) todosHibernateRepository.findAll();

        List<TodoSummary> summaries = todosHibernate.stream()
                .map(todoHibernate -> new TodoSummary(todoHibernate.id, todoHibernate.title)).collect(Collectors.toList());

        return summaries;
    }

    @Override
    public Optional<Todo> readTodo(Long id) {
        Optional<TodoHibernate> todoHibernate = todosHibernateRepository.findById(id);
        return todoHibernate.map(TodoHibernateMapper::fromTodoHibernate);
    }

    @Override
    public Long create(Todo todo) {
        TodoHibernate todoHibernate = TodoHibernateMapper.fromTodo(todo);
        TodoHibernate saved = this.todosHibernateRepository.save(todoHibernate);
        return saved.id;
    }

    @Override
    public void update(Todo todo) {
        Optional<TodoHibernate> opt = todosHibernateRepository.findById(todo.getId());

        opt.map(th -> {
            TodoHibernateMapper.mapHibernateFields(todo, th);
            return th;
        }).orElseThrow(() -> new IllegalArgumentException());

        //... and let hibernate dirty checking do the magic :)
        //of course you have to be in a transaction
    }

    @Override
    public void delete(Long id) {
        todosHibernateRepository.deleteById(id);
    }
}
