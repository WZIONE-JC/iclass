package com.iclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:spring/applicationContext-*.xml"})
@SpringBootApplication
public class IclassUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(IclassUserApplication.class, args);
	}
}
