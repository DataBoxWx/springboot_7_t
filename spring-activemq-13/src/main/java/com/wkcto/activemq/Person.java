package com.wkcto.activemq;

import java.io.Serializable;

/**
 * ClassName:Person
 * package:com.wkcto.activemq
 * Description:
 *
 * @Data:2018/8/3 19:06
 * @Auther:wangxin
 */
public class Person implements Serializable{

    private static final long serialVersionUID = -617508232666752916L;

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
