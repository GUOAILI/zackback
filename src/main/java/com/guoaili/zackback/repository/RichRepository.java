package com.guoaili.zackback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guoaili.zackback.entity.Rich;

@Repository
public interface RichRepository extends JpaRepository<Rich,Long> {
    
}
