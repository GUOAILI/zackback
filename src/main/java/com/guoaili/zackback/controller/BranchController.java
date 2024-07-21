package com.guoaili.zackback.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guoaili.zackback.entity.Branch;
import com.guoaili.zackback.service.BranchService;

@RestController
@RequestMapping("/branch")
@CrossOrigin
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping("/all")
    public ResponseEntity<List<Branch>> getAllBranchs() {
        return ResponseEntity.ok(branchService.findAllBranchs());
    }    
    //2024/7/3 
    @PostMapping("/add/one")
    public ResponseEntity<String> addOneBranch(@RequestParam("brhname") String brhname) {
        branchService.addOneBranch(brhname);
        return ResponseEntity.ok("追加子分类成功");
    }
    
    @PostMapping("/delete/one")
    public ResponseEntity<String> deleteOneBranch(@RequestParam("brhname") String brhname) {
        branchService.deleteOneBranch(brhname);
        return ResponseEntity.ok("删除子分类成功");
    }

    
}
