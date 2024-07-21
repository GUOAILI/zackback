package com.guoaili.zackback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.zackback.entity.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {
    @Query(value =  "select * from review where username=?1 and subject=?2 and is_deleted=false",nativeQuery = true)
    List<ReviewEntity> findBySubject(String username,String subject);

    @Transactional
    @Modifying
    @Query(value =  "update review set is_deleted=true where id=?1",nativeQuery = true)
    void logicalDeleteById(long id);
    
}
