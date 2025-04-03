package com.example.committee.controllers;

import com.example.committee.entities.ResidentPermission;
import com.example.committee.services.RPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resident_permissions")
public class RPController {
    @Autowired
    private RPService rpService;
    //分配权限给居民
    @PostMapping
    public ResponseEntity<ResidentPermission> assignPermission(@RequestBody ResidentPermission residentPermission){
        ResidentPermission saveRP = rpService.assignPermission(residentPermission);
        return new ResponseEntity<>(saveRP, HttpStatus.CREATED);
    }
    //id获取关联信息
    @GetMapping("/{id}")
    public ResponseEntity<ResidentPermission> getRPById(@PathVariable Integer id){
        Optional<ResidentPermission> rpOptional = rpService.getRPById(id);
        return rpOptional
                .map(residentPermission -> new ResponseEntity<>(residentPermission, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //居民id获取信息
    @GetMapping("/resident/{residentId}")
    public ResponseEntity<List<ResidentPermission>> getRPByResidentId(@PathVariable Integer residentId){
        List<ResidentPermission> rpList = rpService.getRPByResidentId(residentId);
        return new ResponseEntity<>(rpList, HttpStatus.OK);
    }
    //获取所有
    @GetMapping
    public ResponseEntity<List<ResidentPermission>> getAllRP(){
        List<ResidentPermission> rp = rpService.getAllRP();
        return new ResponseEntity<>(rp, HttpStatus.OK);
    }
    //更新
    @PutMapping
    public ResponseEntity<ResidentPermission> updateRP(@PathVariable Integer id, @RequestBody ResidentPermission updatedRP){
        ResidentPermission residentPermission = rpService.updateRP(id, updatedRP);
        if(residentPermission != null){
            return new ResponseEntity<>(residentPermission, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //删除
    @DeleteMapping
    public ResponseEntity<ResidentPermission> deleteRP(@PathVariable Integer id){
        rpService.deleteRP(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
