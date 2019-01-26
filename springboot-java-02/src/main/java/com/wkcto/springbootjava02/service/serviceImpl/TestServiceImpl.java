package com.wkcto.springbootjava02.service.serviceImpl;

import com.wkcto.springbootjava02.service.TestService;
import org.springframework.stereotype.Service;

/**
 * ClassName:TestServiceImpl
 * package:com.wkcto.springbootjava02.service.serviceImpl
 * Description:
 *
 * @Data:2018/7/25 9:12
 * @Auther:wangxin
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String test() {

        return "test,springboot";
    }
}
