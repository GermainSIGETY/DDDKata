package fr.sauceDallas.getThingsDone.todos.infra.hibernate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodosHibernateRepository extends CrudRepository<TodoHibernate, Long> {
}
