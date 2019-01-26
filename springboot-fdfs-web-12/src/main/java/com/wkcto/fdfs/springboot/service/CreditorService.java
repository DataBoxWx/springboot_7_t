package com.wkcto.fdfs.springboot.service;

import com.wkcto.fdfs.springboot.model.Creditor_info;

import java.util.List;

/**
 * ClassName:CreditorService
 * package:com.wkcto.fdfs.springboot.service
 * Description:
 *
 * @Data:2018/8/1 8:29
 * @Auther:wangxin
 */
public interface CreditorService {

    /**
     * 获取所有债券信息
     * @return
     */
    List<Creditor_info> queryCreditors();

    /**
     * 根据主键获取债券人信息
     * @param id
     * @return
     */
    Creditor_info queryCreditorByKey(Integer id);

    /**
     * 更新债券信息
     * @param creditorInfo
     * @return
     */
    int updateCreditorInfo(Creditor_info creditorInfo);

    /**
     * 删除债券信息
     * @param id
     * @return
     */
    int deleteCreditor(Integer id);
}
