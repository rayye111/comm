package com.example.committee.services;

import com.example.committee.entities.Notice;
import com.example.committee.repositories.NoticeRepository;
import com.example.committee.entities.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;
    // 发布公告并关联发布者
    public Notice saveNoticeWithPublisher(Notice notice, Resident publisher) {
        notice.setPublisherId(publisher);
        return noticeRepository.save(notice);
    }
    //发布公告
    /*
    public Notice saveNotice(Notice notice){
        return noticeRepository.save(notice);
    }
    */
    //id查找公告
    public Optional<Notice> getNoticeById(Integer id){
        return noticeRepository.findById(id);
    }
    //查找所有公告
    public List<Notice> getAllNotices(){
        return noticeRepository.findAll();
    }
    //标题查找
    public List<Notice> getNoticeByTitle(String title){
        return noticeRepository.findByTitleContaining(title);
    }
    //更新
    public Notice updateNotice(Integer id, Notice updatedNotice){
        return noticeRepository.findById(id)
                .map(notice -> {
                    notice.setContent(updatedNotice.getContent());
                    notice.setNoticeId(updatedNotice.getNoticeId());
                    notice.setTitle(updatedNotice.getTitle());
                    notice.setPublisherId(updatedNotice.getPublisherId());
                    notice.setPublishDate(updatedNotice.getPublishDate());
                    return noticeRepository.save(notice);
                })
                .orElse(null);
    }
    //删除
    public void deleteNotice(Integer id){
        noticeRepository.deleteById(id);
    }
}
