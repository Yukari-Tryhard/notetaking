package com.cnpmm.notetaking.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteAddDAO {
    private String userId;
    private String title;
    private String content;

    public NoteAddDAO(String userId, String title, String content) {

        this.userId = userId;
        this.title = title;
        this.content = content;
    }
    public NoteAddDAO(){

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
}
