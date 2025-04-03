package com.example.committee.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeId;
    private String title;
    private String content;
    private Date publishDate = new Date();
    @ManyToOne
    private Resident publisherId;

    //getters
    public Integer getNoticeId(){
        return noticeId;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public Date getPublishDate(){
        return publishDate;
    }
    public Resident getPublisherId(){
        return publisherId;
    }

    //setters
    public void setNoticeId(Integer noticeId){
        this.noticeId = noticeId;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setPublishDate(Date publishDate){
        this.publishDate = publishDate;
    }
    public void setPublisherId(Resident publisherId){
        this.publisherId = publisherId;
    }
}
