package com.wkcto.springboot.service.impl;

import com.wkcto.springboot.mapper.StudentMapper;
import com.wkcto.springboot.model.Student;
import com.wkcto.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:StudentServiceImpl
 * package:com.wkcto.springboot.service.impl
 * Description:
 *
 * @Data:2018/7/26 20:37
 * @Auther:wangxin
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 12000,retries = 0) //等价于<dubbo：service interface="xxx" xxx>
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudentById(String id) {
        System.out.println("*************** " + id);
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStudent(Student student) {

        return studentMapper.updateByPrimaryKeySelective(student);
    }
}
