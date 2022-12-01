package com.cnpmm.notetaking.dto.task;

import ch.qos.logback.core.joran.action.TimestampAction;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.sql.Timestamp;

public class TaskUpdateDTO {
    private String title;
    private String content;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long taskId;
    private boolean isDone;
    public TaskUpdateDTO(){

    }

    public TaskUpdateDTO(String title, String content, Timestamp startDate, Timestamp endDate, Long taskId, boolean isDone){
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskId = taskId;
        this.isDone = isDone;
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

    @JsonProperty("start-date")
    public Timestamp getStartDate() {
        return startDate;
    }

    @JsonProperty("start-date")
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("end-date")
    public Timestamp getEndDate() {
        return endDate;
    }

    @JsonProperty("end-date")
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("task-id")
    public Long getTaskId() {
        return taskId;
    }
    @JsonProperty("task-id")
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @JsonProperty("is-done")
    public boolean isDone() {
        return isDone;
    }

    @JsonProperty("is-done")
    public void setDone(boolean done) {
        isDone = done;
    }
}
