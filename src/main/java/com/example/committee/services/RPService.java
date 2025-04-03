package com.example.committee.services;

import com.example.committee.entities.ResidentPermission;
import com.example.committee.repositories.RPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RPService {
    @Autowired
    private RPRepository rpRepository;
    //分配权限给居民
    public ResidentPermission assignPermission(ResidentPermission residentPermission){
        return rpRepository.save(residentPermission);
    }
    //id获取关联信息
    public Optional<ResidentPermission> getRPById(Integer id){
        return rpRepository.findById(id);
    }
    //居民id获取信息
    public List<ResidentPermission> getRPByResidentId(Integer residentId){
        return rpRepository.findByResidentId_ResidentId(residentId);
    }
    //获取所有
    public List<ResidentPermission> getAllRP(){
        return rpRepository.findAll();
    }
    //更新
    public ResidentPermission updateRP(Integer id, ResidentPermission updatedRP){
        return rpRepository.findById(id)
                .map(rp -> {
                    rp.setRpId(updatedRP.getRpId());
                    rp.setResidentId(updatedRP.getResidentId());
                    rp.setPermissionId(updatedRP.getPermissionId());
                    return rpRepository.save(rp);
                })
                .orElse(null);
    }
    //删除
    public void deleteRP(Integer id){
        rpRepository.deleteById(id);
    }
}
