package com.cnpmm.notetaking.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagDeleteDTO {
    private Long tagId;

    public  TagDeleteDTO(Long tagId){
        this.tagId = tagId;
    }

    public TagDeleteDTO(){

    }
    @JsonProperty("tag-id")
    public Long getTagId() {
        return tagId;
    }
    @JsonProperty("tag-id")
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

}
