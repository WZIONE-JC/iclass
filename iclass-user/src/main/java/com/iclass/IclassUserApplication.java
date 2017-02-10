package com.iclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@MapperScan("com.iclass.user.mybatis.dao")
@SpringBootApplication
public class IclassUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(IclassUserApplication.class, args);
	}
}
