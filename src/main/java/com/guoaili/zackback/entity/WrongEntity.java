package com.guoaili.zackback.entity;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.guoaili.zackback.enumT.Difficulty;
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
@Table(name = "wrong")
public class WrongEntity extends DpjEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    // the common part for all table
    private String username;
    private String school;
    private int grade;
    private boolean is_deleted;
    private String subject;
    // 考试日
    private LocalDate inputDate;
    private String dpjno;
    private String back;
    private String point;
    // 难易度
    @Enumerated(EnumType.STRING)
    private Difficulty easy;

    @Column(length = 500)
    private String correct;

    // @Column(length = 500)
    // private String weakpoint;

    // @Column(length = 600)
    // private String errsum;

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
    
    
