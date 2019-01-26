package com.wkcto.seckill.model;

import java.io.Serializable;

/**
 * ClassName:User
 * package:com.wkcto.seckill.model
 * Description:
 *
 * @Data:2018/8/9 20:23
 * @Auther:wangxin
 */
public class User implements Serializable {
    private  String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
