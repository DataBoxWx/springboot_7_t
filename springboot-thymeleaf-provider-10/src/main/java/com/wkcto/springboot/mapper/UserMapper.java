package com.wkcto.springboot.mapper;

import com.wkcto.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 分页查询
     * @param startRow
     * @param pageSize
     * @return
     */
    List<User> getUserByPage(@Param("startRow") int startRow, @Param("pageSize") int pageSize);

    /**
     * 得到总条数
     * @return
     */
    int getUserTotal();
}