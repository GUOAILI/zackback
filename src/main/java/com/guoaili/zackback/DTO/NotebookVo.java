package com.guoaili.zackback.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotebookVo extends DpjVo {
    private int num;
    private String keyword;
    private String easy;
    private String point;
    private String teacher;
    private String remarks;
    private String post;
    private String subject;
    private List<MultipartFile> files;
}
