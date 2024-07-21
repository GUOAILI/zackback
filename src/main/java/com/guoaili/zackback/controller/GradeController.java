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

import com.guoaili.zackback.entity.Grade;
import com.guoaili.zackback.service.GradeService;


@RestController
@CrossOrigin
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/get")
    public ResponseEntity<Grade> getGrade(){
        return ResponseEntity.ok().body(gradeService.getGrade());
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveGrade(@RequestParam String school,@RequestParam int grade){
        gradeService.saveGrade(school,grade);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteGrade(){
        gradeService.deleteGrade();
        return ResponseEntity.ok().build();
    }
}
