package com.example.committee.services;


import com.example.committee.entities.Resident;
import com.example.committee.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ResidentService {
    @Autowired
    private ResidentRepository residentRepository;
    //添加居民
    public Resident saveResident(Resident resident) {
        return residentRepository.save(resident);
    }
    //id获取居民信息
    public Optional<Resident> getResidentById(Integer id) {
        return residentRepository.findById(id);
    }
    //获取所有居民信息
    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }
    //姓名查询
    public List<Resident> getResidentByName(String name) {
        return residentRepository.findByName(name);
    }
    //更新
    public Resident updateResident(Integer id, Resident updatedResident){
        return residentRepository.findById(id)
                .map(resident -> {
                    resident.setName(updatedResident.getName());
                    resident.setAge(updatedResident.getAge());
                    resident.setGender(updatedResident.getGender());
                    resident.setAddress(updatedResident.getAddress());
                    resident.setResidentId(updatedResident.getResidentId());
                    resident.setPhoneNumber(updatedResident.getPhoneNumber());
                    return residentRepository.save(resident);
                })
                .orElse(null);
    }
    //删除
    public void deleteResident(Integer id){
        residentRepository.deleteById(id);
    }
}
