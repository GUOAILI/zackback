package com.guoaili.zackback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewUpdVo extends ReviewVo{
    private long id;
    private String delImages;    
}
