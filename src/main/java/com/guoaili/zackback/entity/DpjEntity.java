package com.guoaili.zackback.entity;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class DpjEntity {

    // the common part for all table
    private String username;
    private String school;
    private int grade;
    private boolean is_deleted;
    private String subject;

    // store the file url which indicates the location the file is saved at. 
    private List<String> mjddyz;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginday;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modday;
    
}
