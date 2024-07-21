package com.guoaili.zackback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.zackback.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long>{
    // 2024/6/24 add
    Grade findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
