package com.wkcto.springbootmybatis03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringbootMybatis03Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatis03Application.class, args);
	}
}
