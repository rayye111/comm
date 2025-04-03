package com.example.committee.repositories;

import com.example.committee.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    //角色名获取
    Permission findByRole(String role);
}
