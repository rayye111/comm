package com.example.committee.repositories;

import com.example.committee.entities.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    //标题查询
    List<Notice> findByTitleContaining(String title);
}
