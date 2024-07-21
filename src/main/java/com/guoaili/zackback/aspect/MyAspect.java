package com.guoaili.zackback.aspect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.guoaili.zackback.service.serviceImpl.FileStorageServiceImpl;

@Aspect
@Component
public class MyAspect {

    @Before("execution(* com.guoaili.zackback.service.serviceImpl.FileStorageServiceImpl.*(..))")
    public void beforeMethodExecution() {
        // Modify the value of the 'root' variable
        // 2024/06/18 ad comment
        // 此处的日期需要分情况设定，此时切面不能针对类，需要针对方法
        // 1保存的时候，当日
        // 2检索和删除的时候，取该记录中的日期。
        String zpd="uploads/" + String.format(LocalDate.now().toString(),"yyyy-MM-dd");
        FileStorageServiceImpl.root = Paths.get(zpd);
        try{
            Files.createDirectories(FileStorageServiceImpl.root);
        }catch(IOException e){
            throw new RuntimeException("做成当日文件夹失败!");
        }
    }
}