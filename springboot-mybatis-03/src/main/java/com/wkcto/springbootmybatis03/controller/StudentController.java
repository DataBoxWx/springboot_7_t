package com.wkcto.springbootmybatis03.controller;

import com.wkcto.springbootmybatis03.model.Student;
import com.wkcto.springbootmybatis03.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:StudentController
 * package:com.wkcto.springbootmybatis03.controller
 * Description:
 *
 * @Data:2018/7/25 16:31
 * @Auther:wangxin
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/boot/student")
    public @ResponseBody Object student(){
        Student student = studentService.getStudentById("A00001");
        return student;
    }

    @GetMapping("/boot/update")
    public @ResponseBody Object update(){
        int update  = studentService.updateStudent("A00001");

        return update;
    }

}
