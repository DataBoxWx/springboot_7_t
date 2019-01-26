package com.wkcto.springbootmybatis03.service.impl;

import com.wkcto.springbootmybatis03.mapper.StudentMapper;
import com.wkcto.springbootmybatis03.model.Student;
import com.wkcto.springbootmybatis03.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName:StudentServiceImpl
 * package:com.wkcto.springbootmybatis03.service.impl
 * Description:
 *
 * @Data:2018/7/25 16:28
 * @Auther:wangxin
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudentById(String id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        return student;
    }

    @Override
    public int updateStudent(String id) {
        Student student = new Student();
        student.setAge(21);
        student.setId(id);
        student.setName("小龙女");
        int update =  studentMapper.updateByPrimaryKeySelective(student);
        int a = 10 /0;
        return update;
    }
}
