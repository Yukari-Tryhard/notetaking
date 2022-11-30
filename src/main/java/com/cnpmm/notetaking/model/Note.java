package com.cnpmm.notetaking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table()
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;
    private boolean isDelete;
    private String title;
    private String content;
    private Date dateCreated;
    private Date dateUpdated;

    @ManyToOne()
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "note")
    private Collection<Tag> tags = new ArrayList<>();

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.isDelete = false;
    }

    public Note() {

    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }


    public Long getNoteId() {
        return noteId;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
        dateUpdated = dateCreated;
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = new Date();
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
