package com.wkcto.admin.service.impl;

import com.wkcto.admin.mapper.UserInfoMapper;
import com.wkcto.admin.model.UserInfo;
import com.wkcto.admin.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserInfoServiceImpl
 * package:com.wkcto.admin.service.impl
 * Description:
 *
 * @Data:2018/8/15 14:42
 * @Auther:wangxin
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo login(String userName, String password) {

        return userInfoMapper.selectByNameAndPassword(userName,password);
    }
}
