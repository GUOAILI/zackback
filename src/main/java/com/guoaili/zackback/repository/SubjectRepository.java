package com.guoaili.zackback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guoaili.zackback.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    List<Subject> findAll();

    void deleteByChname(String subname);

    // List<Subject> findByChname(String subname);
}