package com.example.committee.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ComplaintSuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer csId;
    @ManyToOne
    private Resident residentId;
    private String content;
    private Date submitDate = new Date();
    private String type;
    private Boolean isHandled;
    @ManyToOne
    private Resident handler;


    //getters
    public Integer getCsId() {
        return csId;
    }

    public Resident getResidentId() {
        return residentId;
    }

    public String getContent() {
        return content;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public String getType() {
        return type;
    }

    public Boolean getIsHandled() {
        return isHandled;
    }

    public Resident getHandler() {
        return handler;
    }

    //setters
    public void setCsId(Integer csId){
        this.csId = csId;
    }
    public void setResidentId(Resident residentId){
        this.residentId = residentId;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setSubmitDate(Date submitDate){
        this.submitDate = submitDate;
    }
    public void setType(String type){
        this.type= type;
    }
    public void setIsHandled(Boolean isHandled){
        this.isHandled = isHandled;
    }
    public void setHandler(Resident handler){
        this.handler = handler;
    }

}
