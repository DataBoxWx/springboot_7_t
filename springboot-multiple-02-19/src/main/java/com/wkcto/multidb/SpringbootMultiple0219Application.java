package com.wkcto.multidb;

import com.wkcto.multidb.model.Person;
import com.wkcto.multidb.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootMultiple0219Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringbootMultiple0219Application.class, args);
		PersonService personService = context.getBean("personServiceImpl", PersonService.class);
		Person person = personService.getPersonById(2);
		System.out.println("姓名：" + person.getName() + "  年龄 ：" + person.getAge());
		person.setName("小花猫");
		person.setAge(1);
		int update = personService.updatePerson(person);
		System.out.println("更新结果 ：" + update);
	}
}
