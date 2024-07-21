package com.guoaili.zackback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WrongUpdVo extends WrongVo {
    private long id;
    private String delImages;
}
