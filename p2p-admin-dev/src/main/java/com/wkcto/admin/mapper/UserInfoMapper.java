package com.wkcto.admin.mapper;

import com.wkcto.admin.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 通过密码和账户名查询用户信息
     * @param userName
     * @param password
     * @return
     */
    UserInfo selectByNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}