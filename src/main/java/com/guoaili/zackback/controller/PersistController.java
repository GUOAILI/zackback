package com.guoaili.zackback.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guoaili.zackback.entity.WritingEntity;
import com.guoaili.zackback.repository.WritingRepository;

@RestController
@CrossOrigin
@RequestMapping("/persist")
public class PersistController {
    @Autowired
    private WritingRepository writingRepository;

    @GetMapping("/serialize/{branch}")
    public ResponseEntity<String> saveObjectTofile(@PathVariable String branch) throws IOException{
        String zpd="persist/" + String.format(LocalDate.now().toString(),"yyyy-MM-dd");
        Path lmj = Paths.get(zpd);
        try{
            Files.createDirectories(lmj);
        }catch(IOException e){
            throw new RuntimeException("做成当日文件夹失败!");
        } 
        SimpleDateFormat customDate=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");

        String uid=customDate.format(new Date());
        Path lcx=lmj.resolve(uid+branch+".ser");
        List<WritingEntity> we=writingRepository.findAll();
        // empty the id column for later reload to another database
        we.forEach(record->{
            record.setId(null);
        });
        FileOutputStream fileOut = null;
        ObjectOutputStream out=null;
        try {
            String ab=Paths.get(lcx.toString()).toAbsolutePath().toString();
            System.out.println(ab);
            fileOut = new FileOutputStream(ab);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(we);
            out.close();
            fileOut.close();
            System.out.println("Serialized writing data to employee.ser");
        } catch (IOException i) {
            out.close();
            fileOut.close();
            throw new RuntimeException("持久化失败!");
        }
        return ResponseEntity.ok("持久化完成");

    }
    @GetMapping("/deserialize/{branch}/{filename}")
    @Transactional
    public ResponseEntity<String> loadfiletoClass(@PathVariable String branch,
                                                @PathVariable String filename
        ) throws IOException{
        String zpd="persist/" + String.format(LocalDate.now().toString(),"yyyy-MM-dd");
        Path lmj = Paths.get(zpd);
        // try{
        //     Files.createDirectories(lmj);
        // }catch(IOException e){
        //     throw new RuntimeException("做成当日文件夹失败!");
        // } 
        Path lcx=lmj.resolve(filename);
        if(!Files.exists(lcx)) throw new RuntimeException("file doesn't exist");
        // List<WritingEntity> we=null;
        // empty the id column for later reload to another database
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(lcx.toFile());
            in = new ObjectInputStream(fileIn);
            Long idx=writingRepository.findTheNextAutoIncrementId();
            List<WritingEntity> we = (List<WritingEntity>) in.readObject();
            // save the deserialize data to table again
            for(int i=0;i<we.size();i++){
                we.get(i).setId(idx+1);
                writingRepository.save(we.get(i));
                idx++;
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            in.close();
            fileIn.close();
            i.printStackTrace();
            throw new RuntimeException("反序列化失败!");
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            in.close();
            fileIn.close();
            c.printStackTrace();
            throw new RuntimeException("反序列化失败!");
        }
 
        return ResponseEntity.ok("反序列化完成");

    }
}
