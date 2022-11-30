package com.cnpmm.notetaking.dto.notebook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotebookAddNoteDTO {
    private Long noteId;
    private Long notebookId;

    public NotebookAddNoteDTO(Long noteId, Long notebookId) {
        this.noteId = noteId;
        this.notebookId = notebookId;
    }

    public NotebookAddNoteDTO() {
    }

    @JsonProperty("note-id")
    public Long getNoteId() {
        return noteId;
    }
    @JsonProperty("note-id")
    public void setNoteId(Long noteId) {
        this.noteId = noteId;
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
