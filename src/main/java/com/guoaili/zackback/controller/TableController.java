package com.guoaili.zackback.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.guoaili.zackback.DTO.ExamUpdVo;
import com.guoaili.zackback.DTO.ExtensionUpdVo;
import com.guoaili.zackback.DTO.NotebookUpdVo;
import com.guoaili.zackback.DTO.ReviewUpdVo;
import com.guoaili.zackback.DTO.WritingUpdVo;
import com.guoaili.zackback.DTO.WrongUpdVo;
import com.guoaili.zackback.entity.ExamEntity;
import com.guoaili.zackback.entity.ExtensionEntity;
import com.guoaili.zackback.entity.NotebookEntity;
import com.guoaili.zackback.entity.ReviewEntity;
import com.guoaili.zackback.entity.WritingEntity;
import com.guoaili.zackback.entity.WrongEntity;
import com.guoaili.zackback.service.TableService;

@RestController
@RequestMapping("/table")
@CrossOrigin
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/writing")
    public ResponseEntity<List<WritingEntity>> getWritingLists(@RequestParam("subject") String subject){
        List<WritingEntity> zpd=tableService.getAllWriting(subject);
        return ResponseEntity.ok().body(zpd);
    }

    @PostMapping("/writing/delete")
    public ResponseEntity<String> deleteOneWriting(@RequestParam("id") long id){
        tableService.deleteOneWriting(id);
        return ResponseEntity.ok().body("删除写作记录成功");
    }
    // 2024/7/1
    @PostMapping("/writing/update")
    public ResponseEntity<String> updateOneWriting( 
            @RequestParam(value = "files",required = false) List<MultipartFile> files, 
            @RequestParam("id") long id,  
            @RequestParam("title") String title,  
            @RequestParam("topic") String topic,  
            @RequestParam("sample") String sample,  
            @RequestParam("comments") String comments,
            @RequestParam("delImages") String delImages) {  
  
        WritingUpdVo wuv=new WritingUpdVo(id,delImages);
                wuv.setComments(comments);
                wuv.setTitile(title);
                wuv.setFiles(files);
                wuv.setSample(sample);
                wuv.setTopic(topic);
        tableService.updateOneWriting(wuv);
        return ResponseEntity.ok().body("更新写作记录成功");
    }
    @PostMapping("/extension/update")
    public ResponseEntity<String> updateOneExtension( 
            @RequestParam(value = "files",required = false) List<MultipartFile> files, 
            @RequestParam("id") long id,  
            @RequestParam("extDate") LocalDate extDate,  
            @RequestParam("abs") String abs,  
            @RequestParam("teacher") String teacher,  
            @RequestParam("easy") String easy,  
            @RequestParam("content") String content,
            @RequestParam("delImages") String delImages) {  
  
        ExtensionUpdVo wuv=new ExtensionUpdVo(id,delImages);
                wuv.setExtDate(extDate);
                wuv.setAbs(abs);
                wuv.setTeacher(teacher);
                wuv.setEasy(easy);
                wuv.setContent(content);
                wuv.setFiles(files);
        tableService.updateOneExtension(wuv);
        return ResponseEntity.ok().body("更新课外课记录成功");
    }
    @PostMapping("/notebook/update")
    public ResponseEntity<String> updateOneNotebook( 
            @RequestParam(value = "files",required = false) List<MultipartFile> files, 
            @RequestParam("id") long id,  
            @RequestParam("num") int num,  
            @RequestParam("keyword") String keyword,  
            @RequestParam("easy") String easy,  
            @RequestParam("point") String point,  
            @RequestParam("teacher") String teacher,
            @RequestParam("remarks") String remarks,
            @RequestParam("post") String post,  
            @RequestParam("delImages") String delImages) {  
  
        NotebookUpdVo wuv=new NotebookUpdVo(id,delImages);
                wuv.setNum(num);
                wuv.setKeyword(keyword);
                wuv.setTeacher(teacher);
                wuv.setEasy(easy);
                wuv.setPoint(point);
                wuv.setRemarks(remarks);
                wuv.setPost(post);
                wuv.setFiles(files);
        tableService.updateOneNotebook(wuv);
        return ResponseEntity.ok().body("更新课本记录成功");
    }

    @PostMapping("/exam/update")
    public ResponseEntity<String> updateOneExam( 
            @RequestParam(value = "files",required = false) List<MultipartFile> files, 
            @RequestParam("id") long id,  
            @RequestParam("examDate") LocalDate examDate,  
            @RequestParam("title") String title,  
            @RequestParam("easy") String easy,  
            @RequestParam("score") int score,  
            @RequestParam("examType") String examType,  
            @RequestParam("evaluation") String evaluation,
            @RequestParam("weakpoint") String weakpoint,
            @RequestParam("errsum") String errsum, 
            @RequestParam("delImages") String delImages) {  
  
        ExamUpdVo wuv=new ExamUpdVo(id,delImages);
                wuv.setExamDate(examDate);
                wuv.setTitle(title);
                wuv.setScore(score);
                wuv.setExamType(examType);
                wuv.setErrsum(errsum);
                wuv.setEvaluation(evaluation);
                wuv.setWeakpoint(weakpoint);
                wuv.setEasy(easy);
                wuv.setFiles(files);
        tableService.updateOneExam(wuv);
        return ResponseEntity.ok().body("更新考题记录成功");
    }

    @PostMapping("/review/update")
    public ResponseEntity<String> updateOneReview( 
            @RequestParam(value = "files",required = false) List<MultipartFile> files, 
            @RequestParam("id") long id,  
            @RequestParam("reviewDate") LocalDate reviewDate,  
            @RequestParam("category") int category,  
            @RequestParam("title") String title,  
            @RequestParam("detail") String detail,  
            @RequestParam("overview") String overview,
            @RequestParam("delImages") String delImages) {  
  
        ReviewUpdVo wuv=new ReviewUpdVo(id,delImages);
        wuv.setTitle(title);
        wuv.setReviewDate(reviewDate);
        wuv.setCategory(category);
        wuv.setDetail(detail);
        wuv.setOverview(overview);
        wuv.setFiles(files);
        tableService.updateOneReview(wuv);
        return ResponseEntity.ok().body("更新复习记录成功");
    }

    @PostMapping("/wrong/update")
    public ResponseEntity<String> updateOneWrong( 
            @RequestParam(value = "files",required = false) List<MultipartFile> files, 
            @RequestParam("id") long id,  
            @RequestParam("inputDate") LocalDate inputDate,  
            @RequestParam("dpjno") String dpjno,  
            @RequestParam("back") String back,  
            @RequestParam("point") String point,  
            @RequestParam("easy") String easy,  
            @RequestParam("correct") String correct,
            @RequestParam("delImages") String delImages) {  
  
        WrongUpdVo wuv=new WrongUpdVo(id,delImages);
        wuv.setInputDate(inputDate);
        wuv.setDpjno(dpjno);
        wuv.setBack(back);
        wuv.setPoint(point);
        wuv.setEasy(easy);
        wuv.setCorrect(correct);
        wuv.setFiles(files);
        tableService.updateOneWrong(wuv);
        return ResponseEntity.ok().body("更新错题记录成功");
    }






    @GetMapping("/notebook")
    public ResponseEntity<List<NotebookEntity>> getNotebookLists(@RequestParam("subject") String subject){
        List<NotebookEntity> zpd=tableService.getAllNotebook(subject);
        return ResponseEntity.ok().body(zpd);
    }

    @PostMapping("/notebook/delete")
    public ResponseEntity<String> deleteOneNotebook(@RequestParam("id") long id){
        tableService.deleteOneNotebook(id);
        return ResponseEntity.ok().body("删除课本记录成功");
    }

    // 2024/6/25 implement
    @GetMapping("/exam")
    public ResponseEntity<List<ExamEntity>> getExamLists(@RequestParam("subject") String subject){
        List<ExamEntity> zpd=tableService.getAllExam(subject);
        return ResponseEntity.ok().body(zpd);
    }

    @PostMapping("/exam/delete")
    public ResponseEntity<String> deleteOneExam(@RequestParam("id") long id){
        tableService.deleteOneExam(id);
        return ResponseEntity.ok().body("删除考题记录成功");
    }

    // 2024/6/26 implement
    @GetMapping("/review")
    public ResponseEntity<List<ReviewEntity>> getReviewLists(@RequestParam("subject") String subject){
        List<ReviewEntity> zpd=tableService.getAllReview(subject);
        return ResponseEntity.ok().body(zpd);
    }

    @PostMapping("/review/delete")
    public ResponseEntity<String> deleteOneReview(@RequestParam("id") long id){
        tableService.deleteOneReview(id);
        return ResponseEntity.ok().body("删除复习记录成功");
    }

    // 2024/6/29 implement
    @GetMapping("/wrong")
    public ResponseEntity<List<WrongEntity>> getWrongLists(@RequestParam("subject") String subject){
        List<WrongEntity> zpd=tableService.getAllWrong(subject);
        return ResponseEntity.ok().body(zpd);
    }

    @PostMapping("/wrong/delete")
    public ResponseEntity<String> deleteOneWrong(@RequestParam("id") long id){
        tableService.deleteOneWrong(id);
        return ResponseEntity.ok().body("删除错题记录成功");
    }
    @GetMapping("/extension")
    public ResponseEntity<List<ExtensionEntity>> getExtensionLists(@RequestParam("subject") String subject){
        List<ExtensionEntity> zpd=tableService.getAllExtension(subject);
        return ResponseEntity.ok().body(zpd);
    }

    @PostMapping("/extension/delete")
    public ResponseEntity<String> deleteOneExtension(@RequestParam("id") long id){
        tableService.deleteOneExtension(id);
        return ResponseEntity.ok().body("删除课外课记录成功");
    }
}
