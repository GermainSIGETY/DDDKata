package fr.sauceDallas.getThingsDone.todos.infra.hibernate;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoUpdatedEventHibernateRepository extends CrudRepository<TodoUpdatedEventHibernate, Long> {

    @VisibleForTesting
    Optional<TodoUpdatedEventHibernate> findByTitle(String title);

    @Query("SELECT e.id from TODO_UPDATED_EVENT e where e.processed= FALSE")
    List<Long> getEventsIdsToProcess();
}
