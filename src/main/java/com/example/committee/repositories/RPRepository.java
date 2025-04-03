package com.example.committee.repositories;

import com.example.committee.entities.ResidentPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RPRepository extends JpaRepository<ResidentPermission, Integer> {
    //居民id查权限
    List<ResidentPermission> findByResidentId_ResidentId(Integer residentId);
}
