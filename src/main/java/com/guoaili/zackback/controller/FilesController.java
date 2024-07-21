package com.guoaili.zackback.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.guoaili.zackback.DTO.ExamVo;
import com.guoaili.zackback.DTO.ExtensionVo;
import com.guoaili.zackback.DTO.FileInfo;
import com.guoaili.zackback.DTO.NotebookVo;
import com.guoaili.zackback.DTO.ResponseMessage;
import com.guoaili.zackback.DTO.ReviewVo;
import com.guoaili.zackback.DTO.WritingVo;
import com.guoaili.zackback.DTO.WrongVo;
import com.guoaili.zackback.service.FileStorageService;


@RestController
@CrossOrigin
@RequestMapping("/localupload")
public class FilesController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile... file){
        String message="";
        try{
            for(MultipartFile zpd : file){
                storageService.save(zpd);
                // message="文件上传成功: " + zpd.getOriginalFilename();
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("文件上传成功"));
        }catch(Exception e){
            message="无法上传文件" + ". 错误原因: "+ e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage((message)));
        }
    }

    // 2024/6/20
    @PostMapping("/baiduwenxin/writing")  
    public ResponseEntity<String> handleFileUpload(  
            @RequestParam(value = "files",required = false) List<MultipartFile> files, 
            @RequestParam("imp") int imp,  
            @RequestParam("title") String title,  
            @RequestParam("topic") String topic,  
            @RequestParam("sample") String sample,  
            @RequestParam("comments") String comments,
            @RequestParam("subject") String subject) {  
  
        WritingVo wv=new WritingVo(imp, title,topic, sample, comments,subject ,files);
        storageService.uploadWriting(wv);

        // 返回响应  
        return new ResponseEntity<>("Files uploaded successfully!", HttpStatus.OK);  
    }
    
    @PostMapping("/baiduwenxin/notebook")  
    public ResponseEntity<String> handleNbFileUpload(  
            @RequestParam(value = "files",required = false) List<MultipartFile> files,  
            @RequestParam("num") int num,  
            @RequestParam("keyword") String keyword,  
            @RequestParam("easy") String easy,  
            @RequestParam("point") String point,  
            @RequestParam("teacher") String teacher,
            @RequestParam("remarks") String remarks,
            @RequestParam("subject") String subject,
            @RequestParam("post") String post) {  
        NotebookVo nv=new NotebookVo(num,keyword, easy, point, teacher, remarks, post,subject, files);
        storageService.uploadNotebook(nv);

        // 返回响应  
        return new ResponseEntity<>("Files uploaded successfully!", HttpStatus.OK);  
    }  

    @PostMapping("/baiduwenxin/exam")  
    public ResponseEntity<String> handleExamFileUpload(  
            @RequestParam(value = "files",required = false) List<MultipartFile> files,  
            @RequestParam("examDate") LocalDate examDate,  
            @RequestParam("title") String title,  
            @RequestParam("easy") String easy,  
            @RequestParam("score") int score,  
            @RequestParam("examType") String examType,  
            @RequestParam("evaluation") String evaluation,
            @RequestParam("weakpoint") String weakpoint,
            @RequestParam("subject") String subject,
            @RequestParam("errsum") String errsum) {  
        ExamVo nv=new ExamVo(examDate,title, easy,score, examType, evaluation, weakpoint, errsum,subject, files);
        storageService.uploadExam(nv);

        // 返回响应  
        return new ResponseEntity<>("Files uploaded successfully!", HttpStatus.OK);  
    }  

    @PostMapping("/baiduwenxin/review")  
    public ResponseEntity<String> handleReviewFileUpload(  
            @RequestParam(value = "files",required = false) List<MultipartFile> files,  
            @RequestParam("reviewDate") LocalDate reviewDate,  
            @RequestParam("category") int category,  
            @RequestParam("title") String title,  
            @RequestParam("detail") String detail,  
            @RequestParam("overview") String overview,
            @RequestParam("subject") String subject) {  
        ReviewVo nv=new ReviewVo(reviewDate,category,title, detail, overview,subject, files);
        storageService.uploadReview(nv);

        // 返回响应  
        return new ResponseEntity<>("Files uploaded successfully!", HttpStatus.OK);  
    }  
    // 2024/6/29
    @PostMapping("/baiduwenxin/wrong")  
    public ResponseEntity<String> handleWrongFileUpload(  
            @RequestParam(value = "files",required = false) List<MultipartFile> files,  
            @RequestParam("inputDate") LocalDate inputDate,  
            @RequestParam("dpjno") String dpjno,  
            @RequestParam("back") String back,  
            @RequestParam("point") String point,  
            @RequestParam("easy") String easy,  
            @RequestParam("correct") String correct,
            @RequestParam("subject") String subject) {  
        WrongVo wv=new WrongVo(inputDate,dpjno,back,point,easy,correct,subject, files);
        storageService.uploadWrong(wv);

        // 返回响应  
        return new ResponseEntity<>("Files uploaded successfully!", HttpStatus.OK);  
    }  
    @PostMapping("/baiduwenxin/extension")  
    public ResponseEntity<String> handleExtensionFileUpload(  
            @RequestParam(value = "files",required = false) List<MultipartFile> files,  
            @RequestParam("extDate") LocalDate extDate,  
            @RequestParam("abs") String abs,  
            @RequestParam("teacher") String teacher,  
            @RequestParam("easy") String easy,  
            @RequestParam("content") String content,
            @RequestParam("subject") String subject) {  
        ExtensionVo wv=new ExtensionVo(extDate,teacher,abs,easy,content,subject, files);
        storageService.uploadExtension(wv);

        // 返回响应  
        return new ResponseEntity<>("Files uploaded successfully!", HttpStatus.OK);  
    }  


    @PostMapping("/upload/writing")
    public ResponseEntity<String> uploadWriting(@RequestBody WritingVo wv) {
        storageService.uploadWriting(wv);
        // todo here 2024/6/19
        return ResponseEntity.ok().body("写入后台成功");
    }
    
    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles(){
        List<FileInfo> fileInfos =storageService.loadAll()
                .map(path-> {
                    String filename=path.getFileName().toString();
                    String url=MvcUriComponentsBuilder
                        .fromMethodName(
                            FilesController.class, 
                            "getFile", 
                            path.getFileName().toString())
                        .build().toString();
                    return new FileInfo(filename, url);
                }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);

// {name: '001.jpg', url: 'http://localhost:9000/localupload/files/001.jpg'}
// {name: '2024-06-17-07-22-15-1c9ecbfbbce4h7', url: 'http://localhost:9000/localupload/files/2024-06-17-07-22-15-1c9ecbfbbce4h7'}
// {name: '2024-06-17-07-22-33-5hca9459dc89f', url: 'http://localhost:9000/localupload/files/2024-06-17-07-22-33-5hca9459dc89f'}
// {name: '2024-06-17-08-19-26-e1e34gbe91309', url: 'http://localhost:9000/localupload/files/2024-06-17-08-19-26-e1e34gbe91309'}
// {name: '2024-06-17-08-26-08-894d09247e10b', url: 'http://localhost:9000/localupload/files/2024-06-17-08-26-08-894d09247e10b'}
// {name: 'OIP-C.jpg', url: 'http://localhost:9000/localupload/files/OIP-C.jpg'}
// {name: '物理001.jpg', url: 'http://localhost:9000/localupload/files/物理001.jpg'}
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file=storageService.load(filename);
        return ResponseEntity.ok().header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\""
            ).body(file);
    }

    // 2024/6/20 add for zhuzhuddyz,for react front side <Image> tag useing only
    // 2024/7/1 the img tag of front end will send this httpRequest implicitly
    // so it will be intecepted by spring security and cannot display the picture.
    // u must skip the authentication to let the request come in
    @GetMapping("/files/{zzday}/{filename:.+}")
    @ResponseBody
    // public ResponseEntity<Resource> getzzFile(@PathVariable Map<String,String> zzDdyz){
    public ResponseEntity<Resource> getzzFile(@PathVariable String zzday,@PathVariable String filename){
        Resource file=storageService.loadzz(zzday,filename);
        return ResponseEntity.ok().header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\""
            ).body(file);
    }

    @GetMapping("/delete/{filename:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename){
        storageService.deleteByName(filename);
        return ResponseEntity.status(HttpStatus.OK).body("删除成功");
    }

    // 2024/6/20 add for zhuzhuddyz,for react front side <Image> tag useing only
    @GetMapping("/delete/{zzday}/{filename:.+}")
    public ResponseEntity<String> deletezzFile(@PathVariable String zzday,@PathVariable String filename){
        storageService.deleteByNamezz(zzday,filename);
        return ResponseEntity.status(HttpStatus.OK).body("删除成功");
    }
}