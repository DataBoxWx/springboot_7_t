package com.wkcto.admin.service;

import com.wkcto.admin.model.UserInfo;

/**
 * ClassName:UserInfoService
 * package:com.wkcto.admin.service
 * Description:
 *
 * @Data:2018/8/15 14:32
 * @Auther:wangxin
 */
public interface UserInfoService {
    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @return
     */
    public UserInfo login(String userName, String password);
}
