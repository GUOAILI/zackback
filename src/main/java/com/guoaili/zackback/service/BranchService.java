package com.guoaili.zackback.service;

import java.util.List;

import com.guoaili.zackback.entity.Branch;

public interface BranchService {

    List<Branch> findAllBranchs();

    void addOneBranch(String brhname);

    void deleteOneBranch(String brhname);
    
}
