package com.example.FilRougeFrontOffice;

import com.example.FilRougeFrontOffice.service.FilesStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
public class FilRougeFrontOfficeApplication {

	@Resource
	FilesStorageService storageService;


	public static void main(String[] args) {
		SpringApplication.run(FilRougeFrontOfficeApplication.class, args);

	}
}
