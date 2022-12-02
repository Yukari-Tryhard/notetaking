package com.cnpmm.notetaking.dto.note;

import com.cnpmm.notetaking.model.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class NoteAddDTO {
    private String userId;
    private String title;
    private String content;
    private Collection<String> tags;

    public NoteAddDTO(String userId, String title, String content, Collection<String> tags) {

        this.userId = userId;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }
    public NoteAddDTO(){

    }

    @JsonProperty("user-id")
    public String getUserId() {
        return userId;
    }
    @JsonProperty("user-id")
    public void setUserId(String userId) {
        this.userId = userId;
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
}
