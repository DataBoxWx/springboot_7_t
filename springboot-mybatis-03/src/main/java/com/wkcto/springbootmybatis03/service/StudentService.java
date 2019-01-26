package com.wkcto.springbootmybatis03.service;

import com.wkcto.springbootmybatis03.model.Student;

/**
 * ClassName:StudentService
 * package:com.wkcto.springbootmybatis03.service
 * Description:
 *
 * @Data:2018/7/25 16:21
 * @Auther:wangxin
 */
public interface StudentService {

    Student getStudentById(String id);

    int updateStudent(String id);
}
