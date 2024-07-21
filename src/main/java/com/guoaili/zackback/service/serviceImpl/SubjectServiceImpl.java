package com.guoaili.zackback.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.zackback.DTO.UptSubject;
import com.guoaili.zackback.entity.Grade;
import com.guoaili.zackback.entity.InitDson;
import com.guoaili.zackback.entity.Subject;
import com.guoaili.zackback.repository.GradeRepository;
import com.guoaili.zackback.repository.InitDsonRepository;
import com.guoaili.zackback.repository.SubjectRepository;
import com.guoaili.zackback.service.SubjectService;
import com.guoaili.zackback.service.UserService;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private InitDsonRepository initDsonRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // @Override
    // public Subject getOneSubject(String subname) {
    //     // in our analysis, the name is unique too, so only one record returned
    //     List<Subject> byName = subjectRepository.findByChname(subname);
    //     Subject gal=new Subject();
    //     if ( byName != null ) gal=byName.get(0);
    //     else gal=null;

    //     return gal;
    // }

    // @Override
    // @Transactional
    // public String updateOneSubject(UptSubject updData) {
    //     Subject guoaili=subjectRepository.findByChname(updData.getSubject()).get(0);
    //     guoaili.setAllsub(updData.getAllsub());
    //     guoaili.setModday(LocalDate.now());
    //     subjectRepository.save(guoaili);
    //     return "OK";
    // }

    // 2024/6/24 add bellow 4 methods
    @Override
    public List<InitDson> getInit() {
        // return initDsonRepository.findAll();
        String username=userService.getUser().getUsername();
        Grade zpd=gradeRepository.findByUsername(username);
        return initDsonRepository.findByUsernameAndSchoolAndGrade(
            userService.getUser().getUsername(),
            zpd.getSchool(),
            zpd.getGrade()
        );
    }

    @Override
    public InitDson getOneInit(String subname) {
        String username=userService.getUser().getUsername();
        Grade xiaofang = gradeRepository.findByUsername(username);
        InitDson byName = initDsonRepository.findByChname(username,xiaofang.getSchool(),xiaofang.getGrade(), subname);
        InitDson gal=new InitDson();
        if ( byName != null ) {
            gal=byName; 
        }else {
            gal=null;
        }
        return gal;
    }

    @Override
    @Transactional
    public String updateOneInit(UptSubject updData) {
        String username=userService.getUser().getUsername();
        Grade xiaofang = gradeRepository.findByUsername(username);
        InitDson xiangyan=initDsonRepository.findByChname(username, xiaofang.getSchool(),xiaofang.getGrade(),updData.getSubject());
        if(xiangyan==null) {
            // create a new record
            InitDson guoaili=new InitDson();
            guoaili.setUsername(username);
            guoaili.setSchool(xiaofang.getSchool());
            guoaili.setGrade(xiaofang.getGrade());
            guoaili.setChname(updData.getSubject());
            guoaili.setAllsub(updData.getAllsub());
            guoaili.setBeginday(LocalDate.now());
            guoaili.setModday(LocalDate.now());
            initDsonRepository.save(guoaili);
        } else {
            xiangyan.setAllsub(updData.getAllsub());
            xiangyan.setModday(LocalDate.now());
            initDsonRepository.save(xiangyan);
        }
        return "OK";
    }

    @Transactional
    @Override
    public void addOneSubject(String subname) {
        Subject sj=new Subject();
        sj.setBeginday(LocalDate.now());
        sj.setModday(LocalDate.now());
        sj.setChname(subname);
        subjectRepository.save(sj);
    }

    @Transactional
    @Override
    public void deleteOneSubject(String subname) {
        subjectRepository.deleteByChname(subname);
    }

    // @Override
    // @Transactional
    // public String createOneInit(UptSubject updData) {
    //     String username="zhouruiou@gmail.com";
    //     Grade xiaofang = gradeRepository.findByUsername(username);
    //     InitDson guoaili=new InitDson();
    //     guoaili.setSchool(xiaofang.getSchool());
    //     guoaili.setGrade(xiaofang.getGrade());
    //     guoaili.setUsername("zhouruiou@gmail.com");
    //     guoaili.setAllsub(updData.getAllsub());
    //     guoaili.setBeginday(LocalDate.now());
    //     guoaili.setModday(LocalDate.now());
    //     initDsonRepository.save(guoaili);
    //     return "OK";
    // }
    
}