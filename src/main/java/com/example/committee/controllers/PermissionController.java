package com.example.committee.controllers;

import com.example.committee.entities.ComplaintSuggestion;
import com.example.committee.entities.Permission;
import com.example.committee.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    //新增权限信息
    @PostMapping
    public ResponseEntity<Permission> addPermission(@RequestBody Permission permission){
        Permission savedPermission = permissionService.savePermission(permission);
        return new ResponseEntity<>(savedPermission, HttpStatus.CREATED);
    }
    //id获取
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Integer id){
        Optional<Permission> optionalPermission = permissionService.getPermissionById(id);
        return optionalPermission
                .map(permission -> new ResponseEntity<>(permission, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //获取所有权限信息
    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermission(){
        try{
            List<Permission> permission = permissionService.getAllPermission();
            if(permission.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(permission, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //角色名查找
    @GetMapping("/role/{role}")
    public ResponseEntity<Permission> getPermissionByRole(@PathVariable String role){
        Permission permission = permissionService.getPermissionByRole(role);
        if(permission != null){
            return new ResponseEntity<>(permission, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //更新
    @PutMapping("/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable Integer id, @RequestBody Permission updatedPermission){
        Permission permission = permissionService.updatePermission(id, updatedPermission);
        if(permission != null){
            return new ResponseEntity<>(permission, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //删除
    @DeleteMapping("/{id}")
    public ResponseEntity<Permission> deletePermission(@PathVariable Integer id){
        permissionService.deletePermission(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
