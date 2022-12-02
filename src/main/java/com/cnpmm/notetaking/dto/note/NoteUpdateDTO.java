package com.cnpmm.notetaking.dto.note;

import com.cnpmm.notetaking.dto.notebook.NotebookUpdateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class NoteUpdateDTO {
    @JsonProperty("user-id")
    private Integer userId;
    @JsonProperty("note-id")
    private Long noteId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("tags")
    private Collection<String> tags;

    public NoteUpdateDTO(){

    }

    public NoteUpdateDTO(Integer userId, Long noteId, String title, String content){
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.userId = userId;
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

    @JsonProperty("tags")
    public Collection<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }
    @JsonProperty("user-id")
    public Integer getUserId() {
        return userId;
    }
    @JsonProperty("user-id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
