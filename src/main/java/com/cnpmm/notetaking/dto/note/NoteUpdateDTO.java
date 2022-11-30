package com.cnpmm.notetaking.dto.note;

import com.cnpmm.notetaking.dto.notebook.NotebookUpdateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteUpdateDTO {
    private Long noteId;
    private String title;
    private String content;

    public NoteUpdateDTO(){

    }

    public NoteUpdateDTO(Long noteId, String title, String content){
        this.noteId = noteId;
        this.title = title;
        this.content = content;
    }

    @JsonProperty("note-id")
    public Long getNoteId() {
        return noteId;
    }
    @JsonProperty("note-id")
    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }
}
