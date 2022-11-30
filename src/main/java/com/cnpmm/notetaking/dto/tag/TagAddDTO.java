package com.cnpmm.notetaking.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagAddDTO {
    private String tagName;
    private Integer userId;


    public TagAddDTO(String tagName, Integer userId) {
        this.tagName = tagName;
        this.userId = userId;
    }

    public TagAddDTO(){

    }
    @JsonProperty("tag-name")
    public String getTagName() {
        return tagName;
    }
    @JsonProperty("tag-name")
    public void setTagName(String tagName) {
        this.tagName = tagName;
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
