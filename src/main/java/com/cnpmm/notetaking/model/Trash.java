package com.cnpmm.notetaking.model;


import javax.persistence.*;

@Entity
@Table()
public class Trash {

    @Id
    private Long trashId;
    private String type;

    public Trash(String type) {
        this.type = type;
    }

    public Trash() {

    }

    public void setTrashId(Long trashId) {
        this.trashId = trashId;
    }

    public Long getTrashId() {
        return trashId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
