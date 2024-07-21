package com.guoaili.zackback.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guoaili.zackback.entity.Grade;
import com.guoaili.zackback.repository.GradeRepository;
import com.guoaili.zackback.service.GradeService;
import com.guoaili.zackback.service.UserService;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private UserService userService;

    @Override
    public Grade getGrade() {
        // Grade one = gradeRepository.findAll().get(0);
        Grade one = gradeRepository.findByUsername(userService.getUser().getUsername());
        return one;
    }

    @Override
    public void saveGrade(String school, int grade) {
        Grade zpddyz001=new Grade();
        // temporary setup 2024/6/28
        zpddyz001.setUsername(userService.getUser().getUsername());
        zpddyz001.setSchool(school);
        zpddyz001.setGrade(grade);
        gradeRepository.save(zpddyz001);
    }

    @Override
    public void deleteGrade() {
        gradeRepository.deleteByUsername(userService.getUser().getUsername());
    }
    
}
