package com.wkcto.fdfs.springboot.mapper;

import com.wkcto.fdfs.springboot.model.Creditor_info;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Creditor_infoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Creditor_info record);

    int insertSelective(Creditor_info record);

    Creditor_info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Creditor_info record);

    int updateByPrimaryKey(Creditor_info record);

    List<Creditor_info> selectAllCreditor();

    /**
     * 更新路径
     * @param creditorInfo
     * @return
     */
    int updateCreditor(Creditor_info creditorInfo);

    /**
     * 删除合同路径
     * @param id
     * @return
     */
    int updateByContract(Integer id);
}