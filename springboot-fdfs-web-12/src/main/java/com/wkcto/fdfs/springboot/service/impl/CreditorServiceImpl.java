package com.wkcto.fdfs.springboot.service.impl;

import com.wkcto.fdfs.springboot.fdfs.FastDFS;
import com.wkcto.fdfs.springboot.mapper.Creditor_infoMapper;
import com.wkcto.fdfs.springboot.model.Creditor_info;
import com.wkcto.fdfs.springboot.service.CreditorService;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * ClassName:CreditorServiceImpl
 * package:com.wkcto.fdfs.springboot.service.impl
 * Description:
 *
 * @Data:2018/8/1 8:30
 * @Auther:wangxin
 */
@Service
public class CreditorServiceImpl implements CreditorService {

    @Autowired
    private Creditor_infoMapper creditorInfoMapper;

    @Override
    public List<Creditor_info> queryCreditors() {

        return creditorInfoMapper.selectAllCreditor();
    }

    @Override
    public Creditor_info queryCreditorByKey(Integer id) {
        return creditorInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateCreditorInfo(Creditor_info creditorInfo) {
        return creditorInfoMapper.updateCreditor(creditorInfo);
    }

    @Transactional
    @Override
    public int deleteCreditor(Integer id) {
        int result = 1 ; //1表示失败。0表示成功
        Creditor_info creditorInfo = creditorInfoMapper.selectByPrimaryKey(id);
        //删除数据库信息
        int update = creditorInfoMapper.updateByContract(id);
        if(update > 0){
            try {
                //删除文件
                int delete = FastDFS.getStorageClient().delete_file(creditorInfo.getGroupname(),creditorInfo.getRemotefilename());
                if(delete == 0){
                    result = 0;
                }else {
                    throw new  RuntimeException("合同删除失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new  RuntimeException("合同删除失败");
            } catch (MyException e) {
                e.printStackTrace();
                throw new  RuntimeException("合同删除失败");
            }
        }


        return result;
    }
}
