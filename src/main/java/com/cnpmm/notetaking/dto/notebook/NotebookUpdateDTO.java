package com.cnpmm.notetaking.dto.notebook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotebookUpdateDTO {
    private Long notebookId;
    private String notebookName;

    public NotebookUpdateDTO(){

    }

    public NotebookUpdateDTO(Long notebookId, String notebookName){
        this.notebookId = notebookId;
        this.notebookName = notebookName;
    }

    @JsonProperty("notebook-name")
    public String getNotebookName() {
        return notebookName;
    }

    @JsonProperty("notebook-name")
    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    @JsonProperty("notebook-id")
    public Long getNotebookId() {
        return notebookId;
    }

    @JsonProperty("notebook-id")
    public void setNotebookId(Long notebookId) {
        this.notebookId = notebookId;
    }
}
