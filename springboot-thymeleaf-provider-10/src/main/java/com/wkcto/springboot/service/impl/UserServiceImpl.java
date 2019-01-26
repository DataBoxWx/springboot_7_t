package com.wkcto.springboot.service.impl;

import com.wkcto.springboot.mapper.UserMapper;
import com.wkcto.springboot.model.User;
import com.wkcto.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:UserServiceImpl
 * package:com.wkcto.springboot.service.impl
 * Description:
 *
 * @Data:2018/7/28 20:59
 * @Auther:wangxin
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public int updateUser(User user) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        int update = userMapper.updateByPrimaryKeySelective(user);
        if(update > 0){
            redisTemplate.opsForValue().set(user.getId(),user,15000,TimeUnit.SECONDS);
        }
        return update;
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int deleteUser(Integer id) {
        int delete = userMapper.deleteByPrimaryKey(id);
        if(delete > 0){
            redisTemplate.delete(id);
        }
        return delete;
    }

    @Override
    public List<User> getUserByPage(int startRow, int pageSize) {

        return userMapper.getUserByPage(startRow,pageSize);
    }

    @Override
    public int getUserByTotal() {
        return userMapper.getUserTotal();
    }

    @Override
    public User getUserById(Integer id) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        User user = (User) redisTemplate.opsForValue().get(id);
        if(user == null){
            synchronized (this){
                user = (User) redisTemplate.opsForValue().get(id);
                if(user == null){
                    user =  userMapper.selectByPrimaryKey(id);
                    redisTemplate.opsForValue().set(id,user,15000, TimeUnit.SECONDS);
                }
            }
        }
        return user;
    }
}
