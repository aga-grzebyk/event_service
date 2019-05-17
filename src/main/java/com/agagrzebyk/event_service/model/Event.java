package com.agagrzebyk.event_service.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat
    private Date date;
    private String name;
    private String address;
    private Access access;
    private String organizer;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_event",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> listOfUsers = new HashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Comment> eventComments = new HashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Participation> eventParticipants = new HashSet<>();

    public Event() {
    }

    public Event(Date date, String name, String address, Access access, String organizer, Set<User> listOfUsers) {
        this.date = date;
        this.name = name;
        this.address = address;
        this.access = access;
        this.organizer = organizer;
        this.listOfUsers = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Set<User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(Set<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public Set<Comment> getEventComments() {
        return eventComments;
    }

    public void setEventComments(Set<Comment> eventComments) {
        this.eventComments = eventComments;
    }

    public Set<Participation> getEventParticipants() {
        return eventParticipants;
    }

    public void setEventParticipants(Set<Participation> eventParticipants) {
        this.eventParticipants = eventParticipants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", access=" + access +
                ", organizer='" + organizer + '\'' +
                ", listOfUsers=" + listOfUsers +
                '}';
    }
}
