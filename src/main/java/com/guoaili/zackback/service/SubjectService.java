package com.guoaili.zackback.service;

import java.util.List;

import com.guoaili.zackback.DTO.UptSubject;
import com.guoaili.zackback.entity.InitDson;
import com.guoaili.zackback.entity.Subject;

public interface SubjectService {

    List<Subject> getAllSubjects();

    // Subject getOneSubject(String subname);

    // String updateOneSubject(UptSubject updData);

    // 2024/6/24 add the bellow 4 methods
    List<InitDson> getInit();

    InitDson getOneInit(String subname);

    String updateOneInit(UptSubject updData);

    void addOneSubject(String subname);

    void deleteOneSubject(String subname);

    // String createOneInit(UptSubject updData);
    
}
