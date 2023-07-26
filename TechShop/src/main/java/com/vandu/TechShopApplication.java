package com.vandu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.vandu.config.StorageProperties;
import com.vandu.service.FileSystemStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TechShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechShopApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(FileSystemStorageService fileSystemStorageService) {
		return (args ->fileSystemStorageService.init()); 
	}

}
