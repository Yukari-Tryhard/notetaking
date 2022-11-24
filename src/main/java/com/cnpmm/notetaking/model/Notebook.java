package com.cnpmm.notetaking.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table()
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notebookId;
    private String notebookName;
    private boolean isDeleted;

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
}
