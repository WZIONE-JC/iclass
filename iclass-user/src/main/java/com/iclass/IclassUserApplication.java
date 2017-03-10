package com.iclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:spring/applicationContext-*.xml"})
@SpringBootApplication
public class IclassUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(IclassUserApplication.class, args);
	}
}
