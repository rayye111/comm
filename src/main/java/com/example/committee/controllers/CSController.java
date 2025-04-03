package com.example.committee.controllers;

import com.example.committee.entities.ComplaintSuggestion;
import com.example.committee.entities.Resident;
import com.example.committee.services.CSService;
import com.example.committee.services.ResidentService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/complaints_suggestions")
public class CSController {
    @Autowired
    private CSService csService;
    @Autowired
    private ResidentService residentService;
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
    //创建投诉建议并关联居民
    @PostMapping("/cRs")
    public ResponseEntity<ComplaintSuggestion> createComplaintSuggestionWithResidents(@RequestBody CreateComplaintSuggestionRequest request) {
        System.out.println("接口已被调用"); // 添加日志
        // 校验 submitterId 非空
        if (request.getSubmitterId() == null) {
            ComplaintSuggestion errorSuggestion = new ComplaintSuggestion();
            errorSuggestion.setMessage("submitterId 不能为 null");
            return new ResponseEntity<>(errorSuggestion, HttpStatus.BAD_REQUEST);
        }
        // 校验 handlerId 非空
        if (request.getHandlerId() == null) {
            ComplaintSuggestion errorSuggestion = new ComplaintSuggestion();
            errorSuggestion.setMessage("handlerId 不能为 null");
            return new ResponseEntity<>(errorSuggestion, HttpStatus.BAD_REQUEST);
        }
        // 查询提交者居民
        Optional<Resident> submitterOptional = residentService.getResidentById(request.getSubmitterId());
        if (submitterOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Resident submitter = submitterOptional.get();

        // 查询处理者居民
        Optional<Resident> handlerOptional = residentService.getResidentById(request.getHandlerId());
        if (handlerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Resident handler = handlerOptional.get();

        ComplaintSuggestion complaintSuggestion = csService.createComplaintSuggestionWithResidents(submitter, handler, request.getContent(), request.getType());
        return new ResponseEntity<>(complaintSuggestion, HttpStatus.CREATED);
    }
    //处理cs
    @GetMapping("/{id}/handle")
    public ResponseEntity<ComplaintSuggestion> handleCS(@PathVariable Integer id, @RequestBody Resident handler){
        List<ComplaintSuggestion> handledCSList = csService.handleCS(id, handler);
        if(handledCSList != null && !handledCSList.isEmpty()){
            return new ResponseEntity<>(handledCSList.get(0), HttpStatus.OK);
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
    public ResponseEntity<Void> deleteCS(@PathVariable Integer id){
        csService.deleteCS(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //请求类
    static class CreateComplaintSuggestionRequest {
        private Integer submitterId;
        private Integer handlerId;
        private String content;
        private String type;

        public Integer getSubmitterId() {
            return submitterId;
        }

        public void setSubmitterId(Integer submitterId) {
            this.submitterId = submitterId;
        }

        public Integer getHandlerId() {
            return handlerId;
        }

        public void setHandlerId(Integer handlerId) {
            this.handlerId = handlerId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    @GetMapping("/test")
    public String test() {
        try {
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}