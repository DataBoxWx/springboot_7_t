package com.wkcto.springboot.controller;

import com.wkcto.springboot.model.Student;
import com.wkcto.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        ExecutorService pool = Executors.newFixedThreadPool(8 * 2);

        for(int i = 0; i < 10000; i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    Student student = studentService.getStudentById("A00001");
                }
            });

        }
        Student student = studentService.getStudentById("A00001");

        return student;
    }

    @GetMapping("/boot/update")
    public @ResponseBody Object update(){
        int update  = studentService.updateStudent("A00001");

        return update;
    }

}
