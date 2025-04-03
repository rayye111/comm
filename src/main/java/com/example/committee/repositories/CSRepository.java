package com.example.committee.repositories;

import com.example.committee.entities.ComplaintSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CSRepository extends JpaRepository<ComplaintSuggestion, Integer> {
    //居民id查询
    List<ComplaintSuggestion> findByResidentId_ResidentId(Integer id);
}