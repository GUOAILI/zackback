package com.guoaili.zackback.DTO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtensionVo extends DpjVo {
    private LocalDate extDate;
    private String teacher;
    private String abs;
    private String easy;
    private String content;
    private String subject;
    private List<MultipartFile> files;       
}
