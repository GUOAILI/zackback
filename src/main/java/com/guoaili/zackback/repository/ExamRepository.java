package com.guoaili.zackback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.zackback.entity.ExamEntity;

public interface ExamRepository extends JpaRepository<ExamEntity,Long> {
    @Query(value =  "select * from exam where username=?1 and subject=?2 and is_deleted=false",nativeQuery = true)
    List<ExamEntity> findBySubject(String username,String subject);

    // 教训2024/6/22更新操作时，一定要加事务
    @Transactional
    @Modifying
    @Query(value =  "update exam set is_deleted=true where id=?1",nativeQuery = true)
    void logicalDeleteById(long id);
    
}
