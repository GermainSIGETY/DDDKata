package fr.sauceDallas.getThingsDone.app.ui.rest.read;

public class TodoJSONResponse {

    public final Long id;

    public final String title;

    public TodoJSONResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
