package com.guoaili.zackback.entity;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "review")
public class ReviewEntity extends DpjEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    // the common part for all table
    private String username;
    private String school;
    private int grade;
    private boolean is_deleted;
    private String subject;
    // 复习日
    private LocalDate reviewDate;
    private int category;
    private String title;

    @Column(length = 500)
    private String detail;
    @Column(length = 500)
    private String overview;


    // store the file url which indicates the location the file is saved at. 
    @Column(length = 10000)
    private List<String> mjddyz;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginday;
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modday;
    
}


