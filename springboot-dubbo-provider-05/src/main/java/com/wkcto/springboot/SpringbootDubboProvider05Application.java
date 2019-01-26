package com.wkcto.springboot;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class SpringbootDubboProvider05Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDubboProvider05Application.class, args);
	}
}
