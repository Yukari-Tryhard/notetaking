package com.cnpmm.notetaking.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagUpdateDTO {
    private Long tagId;
    private String tagName;

    public TagUpdateDTO(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public TagUpdateDTO(){

    }

    @JsonProperty("tag-id")
    public Long getTagId() {
        return tagId;
    }

    @JsonProperty("tag-id")
    public void setTagId(Long tagId) {
        this.tagId = tagId;
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
