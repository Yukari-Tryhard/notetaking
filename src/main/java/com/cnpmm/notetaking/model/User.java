package com.cnpmm.notetaking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table()
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Integer id;
    private String email;
    @JsonIgnore
    private String password;
    @ManyToMany(fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Role> roles = new ArrayList<>();

    @Nullable
    @OneToMany(mappedBy = "user")
    private Collection<Task> tasks = new ArrayList<>();

    @Nullable
    @OneToMany(mappedBy = "user")
    private Collection<Tag> tags = new ArrayList<>();

    @Nullable
    @OneToMany(mappedBy = "user")
    private Collection<Notebook> notebooks = new ArrayList<>();

    @Nullable
    @OneToMany(mappedBy = "user")
    private Collection<Note> notes = new ArrayList<>();

    @Nullable
    @OneToMany(mappedBy = "user")
    private Collection<Trash> trashes = new ArrayList<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }



    public Collection<Role> getRoles() {
        return roles;
    }


    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public User(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public Collection<Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(Collection<Notebook> notebooks) {
        this.notebooks = notebooks;
    }

    public Collection<Note> getNotes() {
        return notes;
    }

    public void setNotes(Collection<Note> notes) {
        this.notes = notes;
    }
}
