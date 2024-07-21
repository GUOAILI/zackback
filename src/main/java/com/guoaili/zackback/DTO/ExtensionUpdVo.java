package com.guoaili.zackback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtensionUpdVo extends ExtensionVo{
    private long id;
    private String delImages;    
}
