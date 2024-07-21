package com.guoaili.zackback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.guoaili.zackback.DTO.UptSubject;
import com.guoaili.zackback.entity.InitDson;
import com.guoaili.zackback.entity.Subject;
import com.guoaili.zackback.service.SubjectService;

@RestController
@RequestMapping("/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubject() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    // 2024/6/24 add get the init info base on the username and grade
    // username will be set with spring security & JWT
    @GetMapping("/initDson")
    public ResponseEntity<List<InitDson>> getInit() {
        // return ResponseEntity.ok(subjectService.getInit());
        return ResponseEntity.ofNullable(subjectService.getInit());
    }

    @PostMapping("/initDson/one")
    public ResponseEntity<InitDson> getOneInit(@RequestParam("subname") String subname) {
        return ResponseEntity.ok(subjectService.getOneInit(subname));
    }
    
    @PostMapping("initDson/update/one")
    public ResponseEntity<String> updateOneInit(@RequestBody UptSubject updData) {
        return ResponseEntity.ok(subjectService.updateOneInit(updData));
    }

    //2024/7/3 
    @PostMapping("/add/one")
    public ResponseEntity<String> addOneSubject(@RequestParam("subname") String subname) {
        subjectService.addOneSubject(subname);
        return ResponseEntity.ok("追加科目成功");
    }
    
    @PostMapping("/delete/one")
    public ResponseEntity<String> deleteOneSubject(@RequestParam("subname") String subname) {
        subjectService.deleteOneSubject(subname);
        return ResponseEntity.ok("删除科目成功");
    }

    // @PostMapping("initDson/create/one")
    // public ResponseEntity<String> createOneInit(@RequestBody UptSubject updData) {
    //     return ResponseEntity.ok(subjectService.createOneInit(updData));
    // }
    // +++++++++++++++++

    // @PostMapping("/one")
    // public ResponseEntity<Subject> getOneSubject(@RequestParam("subname") String subname) {
    //     return ResponseEntity.ok(subjectService.getOneSubject(subname));
    // }

    // @PostMapping("update/one")
    // // public ResponseEntity<String> updateOneSubject(@RequestParam("updData") String updData) {
    // public ResponseEntity<String> updateOneSubject(@RequestBody UptSubject updData) {
    //     return ResponseEntity.ok(subjectService.updateOneSubject(updData));
    // }
}
