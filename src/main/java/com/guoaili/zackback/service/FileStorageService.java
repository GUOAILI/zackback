package com.guoaili.zackback.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.guoaili.zackback.DTO.DpjVo;
import com.guoaili.zackback.DTO.ExamVo;
import com.guoaili.zackback.DTO.ExtensionVo;
import com.guoaili.zackback.DTO.NotebookVo;
import com.guoaili.zackback.DTO.ReviewVo;
import com.guoaili.zackback.DTO.WritingVo;
import com.guoaili.zackback.DTO.WrongVo;

public interface FileStorageService {
    public void init();
    public String save(MultipartFile file);
    public Resource load(String filename);
    public void deleteAll();
    public void deleteByName(String filename);
    public Stream<Path> loadAll();
    public void uploadWriting(WritingVo wv);
    // 2024/6/20 add
    // public Resource loadzz(Map<String, String> zzDdyz);
    public void deleteByNamezz(String zzDate,String filename);
    public Resource loadzz(String zzday, String filename);
    public void uploadNotebook(NotebookVo nv);
    public void uploadExam(ExamVo nv);
    public void uploadReview(ReviewVo nv);
    public void uploadWrong(WrongVo wv);
    public void uploadExtension(ExtensionVo wv);
    <T extends DpjVo>  List<String> saveComingInUploadImageFile(T wv);

}