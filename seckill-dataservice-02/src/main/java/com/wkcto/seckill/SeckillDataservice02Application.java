package com.wkcto.seckill;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDubboConfiguration
@EnableTransactionManagement
public class SeckillDataservice02Application {

	public static void main(String[] args) {
		SpringApplication.run(SeckillDataservice02Application.class, args);
	}
}
