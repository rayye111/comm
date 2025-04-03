package com.example.committee.services;

import com.example.committee.entities.ComplaintSuggestion;
import com.example.committee.entities.Resident;
import com.example.committee.repositories.CSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CSService {
    @Autowired
    private CSRepository csRepository;
    //提交cs（投诉建议）
    public ComplaintSuggestion saveCS(ComplaintSuggestion complaintSuggestion){
        return csRepository.save(complaintSuggestion);
    }
    //id获取cs
    public Optional<ComplaintSuggestion> getCSById(Integer id){
        return csRepository.findById(id);
    }
    //获取所有cs
    public List<ComplaintSuggestion> getAllCS(){
        return csRepository.findAll();
    }
    //居民id获取cs
    public List<ComplaintSuggestion> getCSByResidentId(Integer id){
        return csRepository.findByResidentId_ResidentId(id);
    }
    //处理cs
    public List<ComplaintSuggestion> handleCS(Integer id, Resident handler){
        Optional<ComplaintSuggestion> optionalCS = csRepository.findById(id);
        if(optionalCS.isPresent()){
            ComplaintSuggestion cs = optionalCS.get();
            cs.setIsHandled(true);
            cs.setHandler(handler);
            return Collections.singletonList(csRepository.save(cs));
        }
        return null;
    }
    //更新
    public ComplaintSuggestion updateCS(Integer id, ComplaintSuggestion updatedCS){
        return csRepository.findById(id)
                .map(cs -> {
                    cs.setCsId(updatedCS.getCsId());
                    cs.setContent(updatedCS.getContent());
                    cs.setType(updatedCS.getType());
                    cs.setSubmitDate(updatedCS.getSubmitDate());
                    cs.setResidentId(updatedCS.getResidentId());
                    cs.setIsHandled(updatedCS.getIsHandled());
                    cs.setHandler(updatedCS.getHandler());
                    return csRepository.save(cs);
                })
                .orElse(null);
    }
    //删除
    public void deleteCS(Integer id){
        csRepository.deleteById(id);
    }
}
