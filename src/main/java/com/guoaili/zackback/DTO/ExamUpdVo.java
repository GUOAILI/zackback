package com.guoaili.zackback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExamUpdVo extends ExamVo{
    private long id;
    private String delImages;    
}
