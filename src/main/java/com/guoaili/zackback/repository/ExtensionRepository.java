package com.guoaili.zackback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.zackback.entity.ExtensionEntity;

public interface ExtensionRepository extends JpaRepository<ExtensionEntity,Long> {
   @Query(value =  "select * from extension where username=?1 and subject=?2 and is_deleted=false",nativeQuery = true)
    List<ExtensionEntity> findBySubject(String username,String subject);
    
    @Transactional
    @Modifying
    @Query(value =  "update extension set is_deleted=true where id=?1",nativeQuery = true)
    void logicalDeleteById(long id);
    
}
