package com.guoaili.zackback.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guoaili.zackback.entity.Branch;
import com.guoaili.zackback.repository.BranchRepository;
import com.guoaili.zackback.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<Branch> findAllBranchs() {
        return branchRepository.findAll();
    }

    @Transactional
    @Override
    public void addOneBranch(String brhname) {
        Branch branch=new Branch();
        branch.setChname(brhname);
        branch.setBeginday(LocalDate.now());
        branch.setModday(LocalDate.now());
        branchRepository.save(branch);
    }

    @Transactional
    @Override
    public void deleteOneBranch(String brhname) {
        branchRepository.deleteByChname(brhname);
    }
    
}
