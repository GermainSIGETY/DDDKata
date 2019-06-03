package fr.sauceDallas.getThingsDone.app.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoUpdatedEventHibernate;
import fr.sauceDallas.getThingsDone.todos.infra.hibernate.TodoUpdatedEventHibernateRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ITConfiguration.class)
@TestPropertySource(
        locations = "classpath:application-IT.properties")
public class TodoITsSteps {

    private static String ASSIGNEE = "toto@toto.com";

    private TestRestTemplate restTemplate;

    private TodoWorld todoWorld;

    private TodoUpdatedEventHibernateRepository repository;

    public TodoITsSteps(TestRestTemplate restTemplate, TodoWorld todoWorld, TodoUpdatedEventHibernateRepository repository) {
        this.restTemplate = restTemplate;
        this.todoWorld = todoWorld;
        this.repository = repository;
    }

    @Given("^a Todo with title \"([^\"]*)\" and description \"([^\"]*)\"$")
    public void aTodoWithTitleAndDescription(String title, String description) throws JSONException {

        JSONObject request = new JSONObject();
        request.put("title", title);
        request.put("description", description);
        request.put("assignee", ASSIGNEE);
        request.put("dueDate", 12334);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange("/todos", HttpMethod.POST, entity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        JSONObject object = new JSONObject(responseEntity.getBody());
        Long id = object.getLong("id");

        assertThat(id).isNotNull();
        todoWorld.todoId = id;
    }

    @When("^User read previously created Todo$")
    public void readPreviouslyCreatedTodo() throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity("/todos/" + todoWorld.todoId, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        JSONObject object = new JSONObject(responseEntity.getBody());
        todoWorld.readTitle = object.getString("title");
        todoWorld.readDescription = object.getString("description");
    }

    @Then("^title is \"([^\"]*)\" and description is \"([^\"]*)\"$")
    public void titleIsAndDescriptionIs(String title, String description) {
        assertThat(todoWorld.readTitle).isEqualTo(title);
        assertThat(todoWorld.readDescription).isEqualTo(description);
    }

    @When("^User Change description of previously created Todo with Title \"([^\"]*)\" and description to \"([^\"]*)\"$")
    public void userChangeDescriptionOfPreviouslyCreatedTodoWithTitleAndDescriptionTo(String title, String description) throws Throwable {

        JSONObject request = new JSONObject();
        request.put("title", title);
        request.put("description", description);
        request.put("assignee", ASSIGNEE);
        request.put("dueDate", 12334);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange("/todos/" + todoWorld.todoId, HttpMethod.PUT, entity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT
        );
    }

    @And("^An alert has been sent to user with title \"([^\"]*)\"$")
    public void anAlertHasBeenSentToUserWithTitle(String alertTitle) throws InterruptedException {
        //eventually consistency buddy
        Thread.sleep(1000);

        Optional<TodoUpdatedEventHibernate> opt = repository.findByTitle(alertTitle);
        assertThat(opt.isPresent()).isTrue();
        assertThat(opt.get().processed).isTrue();

    }
}
