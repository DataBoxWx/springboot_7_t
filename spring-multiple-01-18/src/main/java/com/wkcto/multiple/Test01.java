package com.wkcto.multiple;

import com.wkcto.multiple.model.Person;
import com.wkcto.multiple.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName:Test01
 * package:com.wkcto.multiple
 * Description:
 *
 * @Data:2018/8/7 10:16
 * @Auther:wangxin
 */
public class Test01 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonService personService = context.getBean("personServiceImpl", PersonService.class);
        Person person = personService.getPersonById(2);
        System.out.println("姓名：" + person.getName() + "  年龄 ：" + person.getAge());
        person.setName("小花猫");
        person.setAge(1);
        int update = personService.updatePerson(person);
        System.out.println("更新结果 ：" + update);
    }


}
