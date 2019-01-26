package com.wkcto.springboot;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class SpringbootDubboConsumer05Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDubboConsumer05Application.class, args);
	}
}
