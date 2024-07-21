package com.guoaili.zackback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guoaili.zackback.DTO.UserVo;
import com.guoaili.zackback.entity.User;
import com.guoaili.zackback.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register/save")
    public ResponseEntity<String> registration(@RequestBody UserVo uv){
        if (uv.getUsername() == null || uv.getPassword() == null){
            return new ResponseEntity<String>("input is null",
            HttpStatus.BAD_REQUEST);
        }
        User userByName = userService.findByName(uv.getUsername());
        if (userByName != null){
            return new ResponseEntity<String>("用户名已存在！",
            HttpStatus.NOT_ACCEPTABLE);
        }

        userService.saveUser(uv);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserVo> login(@RequestBody UserVo uv) {
        return ResponseEntity.ofNullable(userService.login(uv));
    }
}