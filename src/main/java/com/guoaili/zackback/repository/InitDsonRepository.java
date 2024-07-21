package com.guoaili.zackback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guoaili.zackback.entity.InitDson;

@Repository
public interface InitDsonRepository extends JpaRepository<InitDson,Long> {

    @Query(value = "select * from initdson where username=?1 and school=?2 and grade=?3 and chname=?4",nativeQuery = true)
    InitDson findByChname(String username, String school, int grade, String subname2);

    List<InitDson> findByUsernameAndSchoolAndGrade(String username,String school,int grade);
    
}
