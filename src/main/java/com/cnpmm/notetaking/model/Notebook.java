package com.cnpmm.notetaking.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table()
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notebookId;
    private String notebookName;
    private boolean isDeleted;
    private Date dateCreated;
    private Date dateUpdated;

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Note> notes = new ArrayList<>();

    public Notebook(String notebookName) {
        this.notebookName = notebookName;
        this.isDeleted = false;
    }

    public Notebook() {

    }

    public void setNotebookId(Long notebookId) {
        this.notebookId = notebookId;
    }

    public Long getNotebookId() {
        return notebookId;
    }

    public Collection<Note> getNotes() {
        return notes;
    }

    public void setNotes(Collection<Note> notes) {
        this.notes = notes;
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdated = new Date();
    }

    public void setUser(User user){
        this.user = user;
    }
}
