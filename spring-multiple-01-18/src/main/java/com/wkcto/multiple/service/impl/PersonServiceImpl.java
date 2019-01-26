package com.wkcto.multiple.service.impl;

import com.wkcto.multiple.datasource.DynamicDataSource;
import com.wkcto.multiple.datasource.ThreadLocalHolder;
import com.wkcto.multiple.mapper.PersonMapper;
import com.wkcto.multiple.model.Person;
import com.wkcto.multiple.service.PersonService;
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

    public Person getPersonById(Integer id) {
        ThreadLocalHolder.setHolder(DynamicDataSource.DB3309);
        return personMapper.selectByPrimaryKey(id);
    }

    public int updatePerson(Person person) {

        ThreadLocalHolder.setHolder(DynamicDataSource.DB3307);


        return personMapper.updateByPrimaryKey(person);
    }
}
