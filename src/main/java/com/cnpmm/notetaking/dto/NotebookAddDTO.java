package com.cnpmm.notetaking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotebookAddDTO {
    private String notebookName;
    private Integer userId;

    public NotebookAddDTO(String notebookName, Integer userId) {
        this.notebookName = notebookName;
        this.userId = userId;
    }
    @JsonProperty("get-notebookname")
    public String getNotebookName() {
        return notebookName;
    }
    @JsonProperty("get-notebookname")
    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
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
