package com.guoaili.zackback.service;

import com.guoaili.zackback.DTO.UserVo;
import com.guoaili.zackback.entity.User;

public interface UserService {

    User findByName(String username);

    User getUser();

    void saveUser(UserVo uv);

    UserVo login(UserVo uv);
    
}
