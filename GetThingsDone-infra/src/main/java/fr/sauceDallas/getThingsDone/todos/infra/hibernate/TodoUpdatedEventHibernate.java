package fr.sauceDallas.getThingsDone.todos.infra.hibernate;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "TODO_UPDATED_EVENT")
public class TodoUpdatedEventHibernate {

    @GenericGenerator(
            name = "TodoUpdatedEventSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TODO_UPDATED_EVENT_ID_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "50"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "50")
            }
    )
    @Id
    @GeneratedValue(generator = "TodoUpdatedEventSequenceGenerator")
    @Column(name = "TODO_UPDATED_EVENT_ID")
    public Long id;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "ASSIGNEE")
    public String assignee;

    @Column(name = "OCCURRED_AT")
    public LocalDateTime occurredAt;

    @Column(name = "PROCESSED")
    public Boolean processed;
    
    public TodoUpdatedEventHibernate() {
    }
}
