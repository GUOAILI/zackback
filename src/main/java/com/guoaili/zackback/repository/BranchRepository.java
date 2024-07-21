package com.guoaili.zackback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guoaili.zackback.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
    List<Branch> findAll();

    void deleteByChname(String brhname);
}
