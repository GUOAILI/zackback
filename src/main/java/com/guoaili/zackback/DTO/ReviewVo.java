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
public class ReviewVo extends DpjVo {
    // formData.append('reviewDate', moment(values.examDate).format('YYYY-MM-DD'));  
    // formData.append('category', values.category);  
    // formData.append('title', values.title);  
    // formData.append('detail', values.detail);  
    // formData.append('overview', values.overview);  
    // // a invisible variable that contains the key info of this page,it's nessessary
    // formData.append('subject', localStorage.getItem("branchDetail"));
    private LocalDate reviewDate;
    private int category;
    private String title;
    private String detail;
    private String overview;
    private String subject;
    private List<MultipartFile> files;

}
