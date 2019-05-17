package com.agagrzebyk.event_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String login;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Email
 //   @NotBlank(message = "Message is mandatory")
    private String email;
    private String publicName;

    private int active;

    private String roles = "";
    private String permissions = "";

    @ManyToMany(mappedBy = "listOfUsers")
    @JsonIgnore
    private Set<Event> myEvents = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Comment> myComments = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Participation> myParticipations = new HashSet<>();

    public User() {
    }

    public User(@NotBlank(message = "Name is mandatory") String login, @NotBlank(message = "Password is mandatory") String password, String roles, String permissions) {
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    public User(@NotNull String login, @NotNull String password, @Email String email, String publicName, Set<Event> myEvents) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.publicName = publicName;
        this.myEvents = myEvents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public Set<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(Set<Event> myEvents) {
        this.myEvents = myEvents;
    }

    public Set<Comment> getMyComments() {
        return myComments;
    }

    public void setMyComments(Set<Comment> myComments) {
        this.myComments = myComments;
    }

    public Set<Participation> getMyParticipations() {
        return myParticipations;
    }

    public void setMyParticipations(Set<Participation> myParticipations) {
        this.myParticipations = myParticipations;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(", "));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(", "));
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", publicName='" + publicName + '\'' +
                ", myEvents=" + myEvents +
                '}';
    }
}
