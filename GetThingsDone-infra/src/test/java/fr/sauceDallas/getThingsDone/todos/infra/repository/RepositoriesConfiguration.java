package fr.sauceDallas.getThingsDone.todos.infra.repository;

import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoUpdatedEventHibernateRepository;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodosHibernateRepository;
import fr.sauceDallas.getThingsDone.todos.infrastructure.TodosRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("fr.sauceDallas.getThingsDone.todos.infra.hibernate")
@EntityScan("fr.sauceDallas.getThingsDone.todos.infra.hibernate")
public class RepositoriesConfiguration {

    @Bean
    TodosRepository todosRepository(TodosHibernateRepository todosHibernateRepository) {
        return new TodosRepositoryImpl(todosHibernateRepository);
    }

    @Bean
    TodosUpdatedEventRepositoryImpl todoUpdatedEventRepository(TodoUpdatedEventHibernateRepository repository) {
        return new TodosUpdatedEventRepositoryImpl(repository);
    }
}
