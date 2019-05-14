package fr.sauceDallas.getThingsDone.app;

import fr.sauceDallas.getThingsDone.todos.api.TodosApi;
import fr.sauceDallas.getThingsDone.todos.infra.InfraConfiguration;
import fr.sauceDallas.getThingsDone.todos.infrastructure.TodosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({InfraConfiguration.class})
public class GetThingsDoneConfiguration {

    @Bean
    TodosApi standardTodosApi(TodosRepository repository) {
        return new TodosApi(repository);
    }
}
