package com.guoaili.zackback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // 2024/6/24 add a reserved column for multiple users.
    private String username;

    private String school;

    private int grade;

}
