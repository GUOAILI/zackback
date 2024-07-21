package com.guoaili.zackback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotebookUpdVo extends NotebookVo{
    private long id;
    private String delImages;    
}
