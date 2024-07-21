package com.guoaili.zackback.service.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.guoaili.zackback.DTO.DpjVo;
import com.guoaili.zackback.DTO.ExamVo;
import com.guoaili.zackback.DTO.ExtensionVo;
import com.guoaili.zackback.DTO.NotebookVo;
import com.guoaili.zackback.DTO.ReviewVo;
import com.guoaili.zackback.DTO.WritingVo;
import com.guoaili.zackback.DTO.WrongVo;
import com.guoaili.zackback.controller.FilesController;
import com.guoaili.zackback.entity.DpjEntity;
import com.guoaili.zackback.entity.ExamEntity;
import com.guoaili.zackback.entity.ExtensionEntity;
import com.guoaili.zackback.entity.Grade;
import com.guoaili.zackback.entity.NotebookEntity;
import com.guoaili.zackback.entity.ReviewEntity;
import com.guoaili.zackback.entity.WritingEntity;
import com.guoaili.zackback.entity.WrongEntity;
import com.guoaili.zackback.enumT.Difficulty;
import com.guoaili.zackback.enumT.Important;
import com.guoaili.zackback.repository.ExamRepository;
import com.guoaili.zackback.repository.ExtensionRepository;
import com.guoaili.zackback.repository.GradeRepository;
import com.guoaili.zackback.repository.NotebookRepository;
import com.guoaili.zackback.repository.ReviewRepository;
import com.guoaili.zackback.repository.WritingRepository;
import com.guoaili.zackback.repository.WrongRepository;
import com.guoaili.zackback.service.FileStorageService;
import com.guoaili.zackback.service.UserService;

import jakarta.annotation.PostConstruct;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    // 20240617 add sub folder to identify the source
    // toczpd
    // use spring aop to implement it
    public static Path root=Paths.get("uploads");

    // one approach to maintain a gollbal variable
    // public static List<URI> gZpddyz;

    @Autowired
    private WritingRepository writingRepository;

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private WrongRepository wrongRepository;

    @Autowired
    private ExtensionRepository extensionRepository;

    @Autowired
    private UserService userService;

    @Override
    @PostConstruct
    public void init() {
        try{
            Files.createDirectories(root);
        }catch(IOException e){
            throw new RuntimeException("could not initialize folder fro upload!");
        }
    }

    // a very crucial method for this app
    @Override
    public String save(MultipartFile file) {
        try{
            String xiaofang=file.getOriginalFilename();
            Path zpddyz001=this.root.resolve(file.getOriginalFilename());
            // 2024/6/22 for duplicated file handle deal with treat
            if(zpddyz001.toFile().exists()){
                xiaofang="苹"+(new Random()).nextInt()+"-"+file.getOriginalFilename();
                zpddyz001=this.root.resolve(xiaofang);
            }
            // Path zpddyz001=this.root.resolve(file.getOriginalFilename().contains(".") ? String.format(LocalDate.now().toString(),"yyyy-MM-dd") + "-"+ file.getOriginalFilename() :  file.getOriginalFilename());
            Files.copy(file.getInputStream(), zpddyz001,StandardCopyOption.REPLACE_EXISTING );
            // 
            System.out.println("an upload file is saved,its name is:========"+file.getOriginalFilename());
            return xiaofang;

        }catch(Exception e){
            // if (e instanceof FileAlreadyExistsException){
            //     throw new RuntimeException("名字为"+file.getOriginalFilename()+ "的文件已经存在!");
            // }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try{
            Path file = root.resolve(filename);
            Resource resource=new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("could not read the file!");
            }
        }catch(MalformedURLException e){
            throw new RuntimeException("error: "+ e.getMessage());
        }
    }

    // 2024/6/20 add for zhuzhuddyz
    @Override
    // public Resource loadzz(Map<String,String> zzddyz) {
    public Resource loadzz(String zzday,String filename) {
        try{
            this.root=Paths.get("uploads/"+zzday);;
            Path file = this.root.resolve(filename);
            Resource resource=new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("无法读取文件!");
            }
        }catch(MalformedURLException e){
            throw new RuntimeException("error: "+ e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    // 2024/06/17 it is also necessary to create a method to delete file by id.
    @Override
    public void deleteByName(String filename) {
        // todo
        // FileSystemUtils.deleteRecursively(root.resolve(filename).toFile());
    } 

    @Override
    public void deleteByNamezz(String zzDate,String filename) {
        this.root=Paths.get("uploads/"+zzDate);
        try {
            // 2024/7/2 fix bug
            Files.deleteIfExists(root.resolve(filename));
            // Files.delete(root.resolve(filename));
            System.out.println(root.resolve(filename).toFile()+" is deleted!");
        } catch (IOException e) {
            throw new RuntimeException("删除无用图片异常: "+e.getMessage());
            // e.printStackTrace();
        }
        // FileSystemUtils.deleteRecursively(root.resolve(filename).toFile());
    }    

    @Override
    public Stream<Path> loadAll() {
        try{
            // 20240617 the folder depth is changed to 3
            // return Files.walk(this.root,1)
            return Files.walk(this.root,1)
                .filter(path->!path.equals(this.root))
                .map(this.root::relativize);
        }catch(IOException e)
        {
            throw new RuntimeException("could not load the files!");
        }
    }

    // 2024/7/1 create this method for generic using.
    public <T extends DpjVo>  List<String> saveComingInUploadImageFile(T wv) {
        List<String> xiaofangList=new ArrayList<>();
        try{
            // first store the upload files and images
            List<String> zpdbyz=new ArrayList<>();
            if(wv.getFiles()!=null){
                for(MultipartFile zpd : wv.getFiles()){
                    String xiaofang = save(zpd);
                    xiaofangList.add(xiaofang);
                    String url = MvcUriComponentsBuilder
                        .fromMethodName(
                            FilesController.class, 
                            // "getFile", 
                            // zpd.getOriginalFilename())
                            "getzzFile",
                            String.format(LocalDate.now().toString(),"yyyy-MM-dd"),
                            // zpd.getOriginalFilename())
                            xiaofang)
                        .build().toString();
                    System.out.println("url="+url);
                    zpdbyz.add(url);
                }
            }
            return zpdbyz;
        }catch(Exception ex){
            for(String zpd : xiaofangList){
                deleteByNamezz(String.format(LocalDate.now().toString(),"yyyy-MM-dd"),zpd);
            }
            throw new RuntimeException("后台写入失败!");
        }
    }

    // 2024/7/1 create this method for generic using.
    private <T extends DpjEntity> void setCommonFields(List<String> zpddyz,T zpddbz){
        String username=userService.getUser().getUsername();
        Grade grade = gradeRepository.findByUsername(username);
        zpddbz.setBeginday(LocalDate.now());
        zpddbz.setMjddyz(zpddyz);
        zpddbz.setUsername(username);
        zpddbz.setSchool(grade.getSchool());
        zpddbz.setGrade(grade.getGrade());
        zpddbz.setModday(LocalDate.now());
    }

    @Override
    @Transactional
    public void uploadWriting(WritingVo wv) {
        // 2024/7/1 re write the logic
        List<String> zpdbyz=saveComingInUploadImageFile(wv);
        WritingEntity zpddbz =new WritingEntity();
        setCommonFields(zpdbyz, zpddbz);
        zpddbz.setImp(wv.getImp()==1?Important.低:
                wv.getImp()==2?Important.中:Important.高);
        zpddbz.setSubject(wv.getSubject());
        zpddbz.setComments(wv.getComments());
        zpddbz.setTitle(wv.getTitile());
        zpddbz.setTopic(wv.getTopic());
        zpddbz.setSample(wv.getSample());
        // at last,save to database
        writingRepository.save(zpddbz);
    }

    @Override
    @Transactional
    public void uploadNotebook(NotebookVo nv) {
        List<String> zpdbyz=saveComingInUploadImageFile(nv);
        NotebookEntity zpddbz =new NotebookEntity();
        setCommonFields(zpdbyz, zpddbz);
        zpddbz.setNum(nv.getNum());
        zpddbz.setKeyword(nv.getKeyword());
        zpddbz.setEasy(nv.getEasy().equals("low") ? Difficulty.低 :
                nv.getEasy().equals("medium") ? Difficulty.中 : Difficulty.高);
        zpddbz.setSubject(nv.getSubject());
        zpddbz.setPoint(nv.getPoint());
        zpddbz.setTeacher(nv.getTeacher());
        zpddbz.setRemarks(nv.getRemarks());
        zpddbz.setPost(nv.getPost());;
        // at last,save to database
        notebookRepository.save(zpddbz);
    }

    @Override
    @Transactional
    public void uploadExam(ExamVo nv) {
        List<String> zpdbyz=saveComingInUploadImageFile(nv);
        ExamEntity zpddbz =new ExamEntity();
        setCommonFields(zpdbyz, zpddbz);
        zpddbz.setExamDate(nv.getExamDate());
        zpddbz.setTitle(nv.getTitle());
        zpddbz.setEasy(nv.getEasy().equals("low") ? Difficulty.低 :
                nv.getEasy().equals("medium") ? Difficulty.中 : Difficulty.高);
        zpddbz.setSubject(nv.getSubject());
        zpddbz.setScore(nv.getScore());
        zpddbz.setExamType(nv.getExamType());
        zpddbz.setErrsum(nv.getErrsum());
        zpddbz.setEvaluation(nv.getEvaluation());
        zpddbz.setWeakpoint(nv.getWeakpoint());
        // at last,save to database
        examRepository.save(zpddbz);
    }

    // 2024/6/29
    @Override
    @Transactional
    public void uploadWrong(WrongVo nv) {
        List<String> zpdbyz=saveComingInUploadImageFile(nv);
        WrongEntity zpddbz =new WrongEntity();
        setCommonFields(zpdbyz, zpddbz);
        zpddbz.setInputDate(nv.getInputDate());
        zpddbz.setDpjno(nv.getDpjno());
        zpddbz.setBack(nv.getBack());
        zpddbz.setEasy(nv.getEasy().equals("low") ? Difficulty.低 :
                nv.getEasy().equals("medium") ? Difficulty.中 : Difficulty.高);
        zpddbz.setSubject(nv.getSubject());
        zpddbz.setPoint(nv.getPoint());
        zpddbz.setCorrect(nv.getCorrect());
        // at last,save to database
        wrongRepository.save(zpddbz);
    }

    @Override
    @Transactional
    public void uploadExtension(ExtensionVo nv) {
        List<String> zpdbyz=saveComingInUploadImageFile(nv);
        ExtensionEntity zpddbz =new ExtensionEntity();
        setCommonFields(zpdbyz, zpddbz);
        zpddbz.setExtDate(nv.getExtDate());
        zpddbz.setTeacher(nv.getTeacher());
        zpddbz.setAbs(nv.getAbs());
        zpddbz.setEasy(nv.getEasy().equals("low") ? Difficulty.低 :
                nv.getEasy().equals("medium") ? Difficulty.中 : Difficulty.高);
        zpddbz.setSubject(nv.getSubject());
        zpddbz.setContent(nv.getContent());
        // at last,save to database
        extensionRepository.save(zpddbz);
    }

    @Override
    @Transactional
    public void uploadReview(ReviewVo nv) {
        List<String> zpdbyz=saveComingInUploadImageFile(nv);
        ReviewEntity zpddbz =new ReviewEntity();
        setCommonFields(zpdbyz, zpddbz);
        zpddbz.setReviewDate(nv.getReviewDate());
        zpddbz.setCategory(nv.getCategory());
        zpddbz.setTitle(nv.getTitle());
        zpddbz.setSubject(nv.getSubject());
        zpddbz.setDetail(nv.getDetail());
        zpddbz.setOverview(nv.getOverview());
        // at last,save to database
        reviewRepository.save(zpddbz);
    }


    // @Override
    // @Transactional
    // public void uploadReview(ReviewVo nv) {
    //     try{
    //         String username=userService.getUser().getUsername();
    //         // first store the upload files and images
    //         List<String> zpdbyz=new ArrayList<>();
    //         // 2024/6/22 run err,files is null
    //         if(nv.getFiles()!=null){
    //             for(MultipartFile zpd : nv.getFiles()){
    //                 String xiaofang = save(zpd);
    //                 String url = MvcUriComponentsBuilder
    //                     .fromMethodName(
    //                         FilesController.class, 
    //                         // "getFile", 
    //                         // zpd.getOriginalFilename())
    //                         "getzzFile",
    //                         String.format(LocalDate.now().toString(),"yyyy-MM-dd"),
    //                         xiaofang)
    //                     .build().toString();
    //                 System.out.println("url="+url);
    //                 zpdbyz.add(url);
    //             }
    //         }
    //         // save to database
    //         Grade grade = gradeRepository.findByUsername(username);
    //         ReviewEntity zpddbz =new ReviewEntity();
    //         zpddbz.setBeginday(LocalDate.now());
    //         zpddbz.setMjddyz(zpdbyz);
    //         zpddbz.setReviewDate(nv.getReviewDate());
    //         zpddbz.setCategory(nv.getCategory());
    //         zpddbz.setTitle(nv.getTitle());
    //         zpddbz.setModday(LocalDate.now());
    //         zpddbz.setUsername(username);
    //         zpddbz.setSchool(grade.getSchool());
    //         zpddbz.setGrade(grade.getGrade());
    //         zpddbz.setSubject(nv.getSubject());

    //         zpddbz.setDetail(nv.getDetail());
    //         zpddbz.setOverview(nv.getOverview());
    //         // at last,save to database
    //         reviewRepository.save(zpddbz);

    //     }catch(Exception ex){
    //         for(MultipartFile zpd : nv.getFiles()){
    //             deleteByName(zpd.getOriginalFilename());
    //         }
    //         throw new RuntimeException("后台写入失败!");
    //     }
    // }

}