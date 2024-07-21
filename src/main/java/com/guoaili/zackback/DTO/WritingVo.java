package com.guoaili.zackback.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WritingVo extends DpjVo {
    private int imp;

    private String titile;
    private String topic;
    private String sample;
    private String comments;
    private String subject;

    // private MultipartFile[] file;
    private List<MultipartFile> files;
}
