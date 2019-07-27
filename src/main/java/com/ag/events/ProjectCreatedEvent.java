package com.ag.events;

public class ProjectCreatedEvent {

    private Long id;

    public ProjectCreatedEvent(Long id) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
