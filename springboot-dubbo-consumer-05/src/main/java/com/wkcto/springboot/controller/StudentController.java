package com.wkcto.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wkcto.springboot.model.Student;
import com.wkcto.springboot.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:StudentController
 * package:com.wkcto.springboot.controller
 * Description:
 *
 * @Data:2018/7/26 21:04
 * @Auther:wangxin
 */
@Controller
public class StudentController {

    @Reference(timeout = 15000)
    private StudentService studentService;

    @RequestMapping("/boot/student/{id}")
    public  @ResponseBody Object student(@PathVariable("id") String id){

        return studentService.getStudentById(id);
    }

    @RequestMapping("/boot/update")
    public @ResponseBody Object update(){
        Student student =  new Student();
        student.setId("A00001");
        student.setName("杨过");

        int update = studentService.updateStudent(student);
        return update;
    }
}
