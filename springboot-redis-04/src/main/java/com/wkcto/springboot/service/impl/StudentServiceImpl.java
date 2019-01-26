package com.wkcto.springboot.service.impl;

import com.wkcto.springboot.mapper.StudentMapper;
import com.wkcto.springboot.model.Student;
import com.wkcto.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public  Student getStudentById(String id) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        Student student = (Student) redisTemplate.opsForValue().get("Student");
        if(null == student){
            synchronized (this){
                student = (Student) redisTemplate.opsForValue().get("Student");
                if(null == student){
                    student = studentMapper.selectByPrimaryKey(id);
                    redisTemplate.opsForValue().set("Student",student);
                        System.out.println("第一次数据库");
                }else {
                    System.out.println("双重锁缓存");
                }

            }
        }else {
            System.out.println("缓存");
        }
        return student;
    }

    @Override
    public int updateStudent(String id) {
        Student student = new Student();
        student.setAge(21);
        student.setId(id);
        student.setName("小龙女");
        int update =  studentMapper.updateByPrimaryKeySelective(student);
        return update;
    }


}
