package com.guoaili.zackback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.guoaili.zackback.service.FileStorageService;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ZackbackApplication {
	// @Autowired
	// private FileStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(ZackbackApplication.class, args);
	}

}
// public class ZackbackApplication implements CommandLineRunner {
// 	@Autowired
// 	private FileStorageService storageService;
	
// 	public static void main(String[] args) {
// 		SpringApplication.run(ZackbackApplication.class, args);
// 	}

// 	@Override
// 	public void run(String... args) throws Exception {
// 		storageService.init();
// 	}

// }