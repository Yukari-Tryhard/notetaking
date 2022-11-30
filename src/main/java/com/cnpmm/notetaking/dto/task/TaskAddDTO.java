package com.cnpmm.notetaking.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class TaskAddDTO {
    private String title;
    private String content;
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer userId;

    public TaskAddDTO(){

    }

    public TaskAddDTO(String title, String content, Timestamp startDate, Timestamp endDate, Integer userId){
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
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

    @JsonProperty("user-id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user-id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
