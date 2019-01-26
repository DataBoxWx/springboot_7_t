package com.wkcto.seckill;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class SeckillWeb03Application {

	public static void main(String[] args) {
		SpringApplication.run(SeckillWeb03Application.class, args);
	}
}
