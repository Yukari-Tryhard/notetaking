package com.cnpmm.notetaking.dto.notebook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotebookAddDTO {

    private Integer userId;
    private String notebookName;

    public NotebookAddDTO(Integer userId, String notebookName) {
        this.userId = userId;
        this.notebookName = notebookName;
    }

    public NotebookAddDTO() {
    }

    @JsonProperty("user-id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user-id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("notebook-name")
    public String getNotebookName() {
        return notebookName;
    }

    @JsonProperty("notebook-name")
    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }
}
