package com.guoaili.zackback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WritingUpdVo extends WritingVo {
    private long id;
    private String delImages;
}
