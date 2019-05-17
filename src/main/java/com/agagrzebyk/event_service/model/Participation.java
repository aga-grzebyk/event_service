package com.agagrzebyk.event_service.model;

import javax.persistence.*;

@Entity
public class Participation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Approval organizerApproval;
    private Approval userApproval;

    public Participation() {
    }

    public Participation(Event event, User user, Approval organizerApproval, Approval userApproval) {
        this.event = event;
        this.user = user;
        this.organizerApproval = organizerApproval;
        this.userApproval = userApproval;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Approval getOrganizerApproval() {
        return organizerApproval;
    }

    public void setOrganizerApproval(Approval organizerApproval) {
        this.organizerApproval = organizerApproval;
    }

    public Approval getUserApproval() {
        return userApproval;
    }

    public void setUserApproval(Approval userApproval) {
        this.userApproval = userApproval;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", event=" + event +
                ", user=" + user +
                ", organizerApproval=" + organizerApproval +
                ", userApproval=" + userApproval +
                '}';
    }
}
