package com.example.committee.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionId;
    private String role;
    private Boolean canPublishNotice;
    private Boolean canHandledCS;

    //getters
    public Integer getPermissionId(){
        return permissionId;
    }
    public String getRole(){
        return role;
    }
    public Boolean getCanPublishNotice(){
        return canPublishNotice;
    }
    public Boolean getCanHandledCS(){
        return canHandledCS;
    }

    //setters
    public void setPermissionId(Integer permissionId){
        this.permissionId = permissionId;
    }
    public void setRole(String role){
        this.role = role;
    }
    public void setCanPublishNotice(Boolean canPublishNotice){
        this.canPublishNotice = canPublishNotice;
    }
    public void setCanHandledCS(Boolean canHandledCS){
        this.canHandledCS = canHandledCS;
    }
}
