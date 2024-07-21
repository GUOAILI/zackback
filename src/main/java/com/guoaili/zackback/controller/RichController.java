package com.guoaili.zackback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guoaili.zackback.entity.Rich;
import com.guoaili.zackback.repository.RichRepository;

@RestController
@CrossOrigin
@RequestMapping("/rich")
public class RichController {
    @Autowired
    private RichRepository richRepository;

    @GetMapping("/restore")
    public ResponseEntity<String> getRichText() {
        List<Rich> all = richRepository.findAll();
        if (all!=null) return ResponseEntity.ok(all.get(0).getText());
        else return null;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveRichText(@RequestBody String text) {
        Rich zpd=new Rich();
        zpd.setText(text);
        richRepository.save(zpd);
        return ResponseEntity.ok("save successfully");
    }
    
}
