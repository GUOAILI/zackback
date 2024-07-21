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
public class WrongVo extends DpjVo {
    private LocalDate inputDate;
    private String dpjno;
    private String back;
    private String point;
    private String easy;
    private String correct;
    private String subject;
    private List<MultipartFile> files;    
}
