package com.example.committee.services;

import com.example.committee.entities.Permission;
import com.example.committee.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    //新增权限信息
    public Permission savePermission(Permission permission){
        return permissionRepository.save(permission);
    }
    //id获取
    public Optional<Permission> getPermissionById(Integer id){
        return permissionRepository.findById(id);
    }
    //获取所有权限信息
    public List<Permission> getAllPermission(){
        return permissionRepository.findAll();
    }
    //角色名查找
    public Permission getPermissionByRole(String role){
        return permissionRepository.findByRole(role);
    }
    //更新
    public Permission updatePermission(Integer id, Permission updatedPermission){
        return permissionRepository.findById(id)
                .map(permission -> {
                    permission.setRole(updatedPermission.getRole());
                    permission.setCanPublishNotice(updatedPermission.getCanPublishNotice());
                    permission.setCanHandledCS(updatedPermission.getCanHandledCS());
                    return permissionRepository.save(permission);
                })
                .orElse(null);
    }
    //删除
    public void deletePermission(Integer id){
        permissionRepository.deleteById(id);
    }
}
