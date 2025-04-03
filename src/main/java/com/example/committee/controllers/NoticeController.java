package com.example.committee.controllers;

import com.example.committee.entities.Notice;
import com.example.committee.entities.Resident;
import com.example.committee.services.NoticeService;
import com.example.committee.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notices")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ResidentService residentService;
    // 发布公告并关联发布者
    @PostMapping("/withPublisher/{publisherId}")
    public ResponseEntity<Notice> addNoticeWithPublisher(@RequestBody Notice notice, @PathVariable Integer publisherId) {
        Optional<Resident> publisherOptional = residentService.getResidentById(publisherId);
        if (publisherOptional.isPresent()) {
            Resident publisher = publisherOptional.get();
            Notice savedNotice = noticeService.saveNoticeWithPublisher(notice, publisher);
            return new ResponseEntity<>(savedNotice, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //发布公告
    /*
    @PostMapping
    public ResponseEntity<Notice> addNotice(@RequestBody Notice notice){
        Notice savedNotice = noticeService.saveNotice(notice);
        return new ResponseEntity<>(savedNotice, HttpStatus.CREATED);
    }
     */
    //id查找公告
    @GetMapping("/{id}")
    public ResponseEntity<Notice> getNoticeById(@PathVariable Integer id){
        return noticeService.getNoticeById(id)
                .map(notice -> new ResponseEntity<>(notice, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //查找所有公告
    @GetMapping
    public ResponseEntity<List<Notice>> getAllNotice(){
        List<Notice> notices = noticeService.getAllNotices();
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }
    //标题查找
    @GetMapping("/search")
    public ResponseEntity<Notice> getNoticeByTitle(@PathVariable String title){
        List<Notice> notices = noticeService.getNoticeByTitle(title);
        if(notices.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(notices.get(0));
    }
    //更新
    @PutMapping("/{id}")
    public ResponseEntity<Notice> updateNotice(@PathVariable Integer id, @RequestBody Notice updatedNotice){
        Notice notice = noticeService.updateNotice(id, updatedNotice);
        if(notice != null){
            return new ResponseEntity<>(notice, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //删除
    @DeleteMapping("/{id}")
    public ResponseEntity<Notice> deleteNotice(@PathVariable Integer id){
        noticeService.deleteNotice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
