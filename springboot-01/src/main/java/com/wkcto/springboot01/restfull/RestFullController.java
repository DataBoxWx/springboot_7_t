package com.wkcto.springboot01.restfull;

import com.wkcto.springboot01.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:RestFullController
 * package:com.wkcto.springboot01.restfull
 * Description:
 *  restfull一般都是返回json数据
 * @Data:2018/7/27 9:25
 * @Auther:wangxin
 */
@RestController
public class RestFullController {

    @GetMapping("/boot/order/{id}/{status}")
   public  Object order1(@PathVariable("id") Integer id,@PathVariable("status") Integer status){
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        order.setName("小芳");
        return  order;
    }

    @PostMapping("/boot/order/{id}/{status}")
    public  Object order2(@PathVariable("id") Integer id,@PathVariable("status") Integer status){
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        order.setName("小芳");
        return  order;
    }

}
