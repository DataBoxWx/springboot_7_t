package com.wkcto.seckill.service;

import com.wkcto.seckill.commons.CommonsReturnObject;
import com.wkcto.seckill.model.Goods;
import com.wkcto.seckill.model.Orders;

import java.util.List;

/**
 * ClassName:GoodsService
 * package:com.wkcto.seckill.service
 * Description:
 *
 * @Data:2018/8/8 10:12
 * @Auther:wangxin
 */
public interface GoodsService {
    List<Goods> getAllGoods();

    /**
     * 根据Id获取商品信息
     * @param id
     * @return
     */
    Goods getGoodsById(Integer id);

    /**
     * 处理秒杀
     * @param uid
     * @param id
     * @param random
     * @return
     */
    CommonsReturnObject execSeckill(String uid, Integer id, String random);

    /**
     * 生成订单
     * @param orders
     * @return
     */
    int createOrders(Orders orders);

    /**
     * 处理异常
     * @param orders
     */
    void processException(Orders orders);

    /**
     * 订单结果查询
     * @param id
     * @param id1
     * @return
     */
    CommonsReturnObject queryResult(String id, Integer id1);

    /**
     * 同步redis中的商品数量到mysql中
     * @param i
     * @param store
     */
    void updateStore(int i, Integer store);
}
