package com.cnpmm.notetaking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagAddDTO {

    private Integer userId;
    private String tagName;

    public TagAddDTO(Integer userId, String tagName) {
        this.userId = userId;
        this.tagName = tagName;
    }

    public TagAddDTO(){

    }

    @JsonProperty("user-id")
    public Integer getUserId() {
        return userId;
    }
    @JsonProperty("user-id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @JsonProperty("tag-name")
    public String getTagName() {
        return tagName;
    }
    @JsonProperty("tag-name")
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
