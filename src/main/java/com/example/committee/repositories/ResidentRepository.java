package com.example.committee.repositories;

import com.example.committee.entities.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    //姓名查询
    List<Resident> findByName(String name);
}
