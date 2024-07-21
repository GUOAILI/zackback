package com.guoaili.zackback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.zackback.entity.WritingEntity;

@Repository
public interface WritingRepository extends JpaRepository<WritingEntity,Long> {

    @Query(value =  "select * from writing where username=?1 and subject=?2 and is_deleted=false",nativeQuery = true)
    List<WritingEntity> findBySubject(String username,String subject);
    
    // @Query("select cdr from CardReq cdr where cdr.status = CheckResult.Pending")
    // @Query(value =  "select * from users where id in (select distinct a.user_id  FROM users_roles as a inner join roles as b on a.role_id=b.id where b.role_name <>'ADMIN')",nativeQuery = true)
    @Transactional
    @Modifying
    @Query(value =  "update writing set is_deleted=true where id=?1",nativeQuery = true)
    void deleteByIdLogical(long id);

    // @Query(value =  "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'zack' AND TABLE_NAME = 'writing'",nativeQuery = true)
    @Query(value = "select max(id) from writing",nativeQuery = true)
    Long findTheNextAutoIncrementId();

}
