package com.cnpmm.notetaking.model;

import javax.persistence.*;

@Entity
@Table()
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Tag() {

    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
