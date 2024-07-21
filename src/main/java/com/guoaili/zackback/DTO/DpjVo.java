package com.guoaili.zackback.DTO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// 2024/7/1 new
// the father class for all the vo sub class such as writing,review,notebook etc.
@Data
public class DpjVo {
    // the common part for all table
    private List<MultipartFile> files;
    private String delImages;    
}
