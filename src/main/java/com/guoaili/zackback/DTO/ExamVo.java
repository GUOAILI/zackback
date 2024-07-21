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
public class ExamVo extends DpjVo {
            // @RequestParam("examDate") LocalDate examDate,  
            // @RequestParam("easy") String easy,  
            // @RequestParam("score") int score,  
            // @RequestParam("examType") String examType,  
            // @RequestParam("evaluation") String evaluation,
            // @RequestParam("weakpoint") String weakpoint,
            // @RequestParam("subject") String subject,
            // @RequestParam("errsum") String errsum) {  
    private LocalDate examDate;
    private String title;
    private String easy;
    private int score;
    private String examType;
    private String evaluation;
    private String weakpoint;
    private String errsum;
    private String subject;
    private List<MultipartFile> files;
    
}
