package com.wkcto.springbootjava02;

import com.wkcto.springbootjava02.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootJava02Application implements CommandLineRunner{

	@Autowired
	private TestService testService;

	/*public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringbootJava02Application.class, args);
		TestService testService = (TestService) context.getBean("testServiceImpl");
		String  s = testService.test();
		System.out.println(s);
	}*/
	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(SpringbootJava02Application.class);
		//springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);

	}

	@Override
	public void run(String... args) throws Exception {
		String test = testService.test();
		System.out.println(test);
	}
}
