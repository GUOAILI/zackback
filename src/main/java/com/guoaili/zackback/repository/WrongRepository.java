package com.guoaili.zackback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.zackback.entity.WrongEntity;

@Repository
public interface WrongRepository extends JpaRepository<WrongEntity,Long> {
   @Query(value =  "select * from wrong where username=?1 and subject=?2 and is_deleted=false",nativeQuery = true)
    List<WrongEntity> findBySubject(String username,String subject);
    
    // @Query("select cdr from CardReq cdr where cdr.status = CheckResult.Pending")
    // @Query(value =  "select * from users where id in (select distinct a.user_id  FROM users_roles as a inner join roles as b on a.role_id=b.id where b.role_name <>'ADMIN')",nativeQuery = true)
    @Transactional
    @Modifying
    @Query(value =  "update wrong set is_deleted=true where id=?1",nativeQuery = true)
    void logicalDeleteById(long id);
    
}
