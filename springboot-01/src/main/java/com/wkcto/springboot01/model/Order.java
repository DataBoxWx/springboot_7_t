package com.wkcto.springboot01.model;

/**
 * ClassName:Order
 * package:com.wkcto.springboot01.model
 * Description:
 *
 * @Data:2018/7/27 9:31
 * @Auther:wangxin
 */
public class Order {
    private Integer id;
    private Integer status;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
