package com.guoaili.zackback.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoaili.zackback.enumT.Important;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "writing")
public class WritingEntity extends DpjEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // the common part for all table
    private String username;
    private String school;
    private int grade;
    private boolean is_deleted;
    
    private String subject;

    @Enumerated(EnumType.STRING)
    private Important imp;

    private String title;
    private String topic;

    @Column(length = 100000)
    private String sample;

    @Column(length = 500)
    private String comments;

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
