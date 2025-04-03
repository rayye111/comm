package com.example.committee.controllers;

import com.example.committee.entities.Resident;
import com.example.committee.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
public class ResidentController {
    @Autowired
    private ResidentService residentService;
    //添加居民
    @PostMapping
    public ResponseEntity<Resident> addResident(@RequestBody Resident resident){
        Resident savedResident = residentService.saveResident(resident);
        return new ResponseEntity<>(savedResident, HttpStatus.CREATED);
    }
    //id查找居民
    @GetMapping("/{id}")
    public ResponseEntity<Resident> getResident(@PathVariable Integer id){
        return residentService.getResidentById(id)
                .map(resident -> new ResponseEntity<>(resident, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //查找所有居民信息
    @GetMapping
    public ResponseEntity<List<Resident>> getAllResidents(){
        List<Resident> residents = residentService.getAllResidents();
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }
    //姓名查找
    @GetMapping("/name")
    public ResponseEntity<Resident> getResident(@PathVariable String name){
        List<Resident> residents = residentService.getResidentByName(name);
        if (residents == null || residents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(residents.get(0));
    }
    //更新
    @PutMapping("/{id}")
    public ResponseEntity<Resident> updateResident(@PathVariable Integer id, @RequestBody Resident updatedResident){
        Resident resident = residentService.updateResident(id, updatedResident);
        if(updatedResident != null){
            return new ResponseEntity<>(resident, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //删除
    @DeleteMapping("/{id}")
    public ResponseEntity<Resident> deleteResident(@PathVariable Integer id){
        residentService.deleteResident(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
