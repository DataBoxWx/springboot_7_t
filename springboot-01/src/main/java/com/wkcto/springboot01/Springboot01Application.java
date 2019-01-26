package com.wkcto.springboot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.wkcto.springboot01.servlet","com.wkcto.springboot01.filter"})
public class Springboot01Application {

	public static void main(String[] args) {

		SpringApplication.run(Springboot01Application.class, args);

	}
}
