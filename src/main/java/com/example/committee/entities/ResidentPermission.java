package com.example.committee.entities;

import jakarta.persistence.*;

@Entity
public class ResidentPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rpId;
    @ManyToOne
    private Resident residentId;
    @ManyToOne
    private Resident permissionId;

    //getters
    public Integer getRpId(){
        return rpId;
    }
    public Resident getResidentId(){
        return residentId;
    }
    public Resident getPermissionId(){
        return permissionId;
    }

    //setters
    public void setRpId(Integer rpId){
        this.rpId = rpId;
    }
    public void setResidentId(Resident residentId){
        this.residentId = residentId;
    }
    public void setPermissionId(Resident permissionId){
        this.permissionId = permissionId;
    }

}
