package fr.sauceDallas.getThingsDone.todos.infra.repository;

import fr.sauceDallas.getThingsDone.common.events.TodoUpdatedEvent;
import fr.sauceDallas.getThingsDone.common.infrastructure.TodosUpdatedEventRepository;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoUpdatedEventHibernate;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoUpdatedEventHibernateRepository;

import java.util.List;
import java.util.Optional;

public class TodosUpdatedEventRepositoryImpl implements TodosUpdatedEventRepository {

    private TodoUpdatedEventHibernateRepository repository;

    public TodosUpdatedEventRepositoryImpl(TodoUpdatedEventHibernateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TodoUpdatedEvent> readEvent(Long id) {
        Optional<TodoUpdatedEventHibernate> eventHibernate = repository.findById(id);
        return eventHibernate.map(TodoUpdatedEventHibernateMapper::fromEventHibernate);
    }

    @Override
    public Long create(TodoUpdatedEvent event) {
        TodoUpdatedEventHibernate eventHibernate = TodoUpdatedEventHibernateMapper.fromTodoUpdatedEvent(event);
        TodoUpdatedEventHibernate saved = this.repository.save(eventHibernate);
        return saved.id;
    }

    @Override
    public void update(TodoUpdatedEvent event) {
        Optional<TodoUpdatedEventHibernate> opt = repository.findById(event.getId());
        opt.map(th -> {
            TodoUpdatedEventHibernateMapper.mapHibernateFields(event, th);
            return th;
        }).orElseThrow(() -> new IllegalArgumentException());
        //... and let hibernate dirty checking do the magic :)
        //of course you have to be in a transaction
    }

    @Override
    public List<Long> getEventsIdsToProcess() {
        return this.repository.getEventsIdsToProcess();
    }
}
