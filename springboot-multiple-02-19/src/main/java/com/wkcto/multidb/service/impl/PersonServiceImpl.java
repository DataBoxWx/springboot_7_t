package com.wkcto.multidb.service.impl;

import com.wkcto.multidb.datasoruce.DynamicDataSource;
import com.wkcto.multidb.datasoruce.ThreadLocalHolder;
import com.wkcto.multidb.mapper.PersonMapper;
import com.wkcto.multidb.model.Person;
import com.wkcto.multidb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:PersonServiceImpl
 * package:com.wkcto.multiple.service.impl
 * Description:
 *
 * @Data:2018/8/7 10:08
 * @Auther:wangxin
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;
    @Override
    public Person getPersonById(Integer id) {
        ThreadLocalHolder.setHolder(DynamicDataSource.DB3309);
        return personMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updatePerson(Person person) {

        ThreadLocalHolder.setHolder(DynamicDataSource.DB3307);


        return personMapper.updateByPrimaryKey(person);
    }
}
