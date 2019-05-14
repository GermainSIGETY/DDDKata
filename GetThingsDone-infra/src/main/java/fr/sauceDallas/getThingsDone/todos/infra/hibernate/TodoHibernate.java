package fr.sauceDallas.getThingsDone.todos.infra.hibernate;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "TODO")
public class TodoHibernate {

    @GenericGenerator(
            name = "TODOSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "TODO_ID_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "50"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "50")
            }
    )
    @Id
    @GeneratedValue(generator = "TODOSequenceGenerator")
    @Column(name = "TODO_ID")
    public Long id;

    @Column(name = "TITLE")
    public String title;

    @Column(name = "DESCRIPTION")
    public String description;

    @Column(name = "CREATION_DATE")
    public LocalDateTime creationDateTime;

    @Column(name = "DUE_DATE")
    public LocalDateTime dueDateTime;

    public TodoHibernate() {
    }
}
