package com.wkcto.springboot.service;

import com.wkcto.springboot.model.User;

import java.util.List;

/**
 * ClassName:UserService
 * package:com.wkcto.springboot.service
 * Description:
 *
 * @Data:2018/7/28 20:53
 * @Auther:wangxin
 */

public interface UserService {

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 增加用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int deleteUser(Integer id);

    /**
     * 分页查询
     * @param startRow
     * @param pageSize
     * @return
     */
    List<User> getUserByPage(int startRow,int pageSize);

    /**
     *用户总数
     * @return
     */
    int getUserByTotal();

    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
    User getUserById(Integer id);

}
