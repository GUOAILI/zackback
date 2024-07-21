package com.guoaili.zackback.service;

import com.guoaili.zackback.entity.Grade;

public interface GradeService {
    Grade getGrade();
    void saveGrade(String school, int grade);
    void deleteGrade();
}
