package com.example.committee.controllers;

import com.example.committee.entities.ComplaintSuggestion;
import com.example.committee.entities.Resident;
import com.example.committee.services.CSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints_suggestions")
public class CSController {
    @Autowired
    private CSService csService;
    //提交cs（投诉建议）
    @PostMapping
    public ResponseEntity<ComplaintSuggestion> addCS(@RequestBody ComplaintSuggestion complaintSuggestion){
        ComplaintSuggestion savedCS = csService.saveCS(complaintSuggestion);
        return new ResponseEntity<>(savedCS, HttpStatus.CREATED);
    }
    //id获取cs
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintSuggestion> getCSById(@PathVariable Integer id){
        return csService.getCSById(id)
                .map(complaintSuggestion -> new ResponseEntity<>(complaintSuggestion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //获取所有cs
    @GetMapping
    public ResponseEntity<List<ComplaintSuggestion>> getAllCS(){
        List<ComplaintSuggestion> cs = csService.getAllCS();
        return new ResponseEntity<>(cs, HttpStatus.OK);
    }
    //居民id获取cs
    @GetMapping("/resident/{residentId}")
    public ResponseEntity<ComplaintSuggestion> getCSByResidentId(@PathVariable Integer id){
        try {
            List<ComplaintSuggestion> cs = csService.getCSByResidentId(id);
            if(cs.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(cs.get(0));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //处理cs
    @GetMapping("/{id}/handle")
    public ResponseEntity<ComplaintSuggestion> handleCS(@PathVariable Integer id, @RequestBody Resident handler){
        ComplaintSuggestion handledCS = (ComplaintSuggestion) csService.handleCS(id, handler);
        if(handledCS != null){
            return new ResponseEntity<>(handledCS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //更新
    @PutMapping("/{id}")
    public ResponseEntity<ComplaintSuggestion> updateCS(@PathVariable Integer id, @RequestBody ComplaintSuggestion updatedCS){
        ComplaintSuggestion cs = csService.updateCS(id, updatedCS);
        if(cs != null){
            return new ResponseEntity<>(cs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //删除
    @DeleteMapping("/{id}")
    public ResponseEntity<ComplaintSuggestion> deleteCS(@PathVariable Integer id){
        csService.getCSById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}