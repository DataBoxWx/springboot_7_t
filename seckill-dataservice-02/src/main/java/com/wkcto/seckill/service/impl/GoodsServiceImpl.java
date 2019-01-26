package com.wkcto.seckill.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.wkcto.seckill.activemq.SendMessage;
import com.wkcto.seckill.commons.CommonsConstants;
import com.wkcto.seckill.commons.CommonsReturnObject;
import com.wkcto.seckill.mapper.GoodsMapper;
import com.wkcto.seckill.mapper.OrdersMapper;
import com.wkcto.seckill.model.Goods;
import com.wkcto.seckill.model.Orders;
import com.wkcto.seckill.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName:GoodsServiceImpl
 * package:com.wkcto.seckill.service.impl
 * Description:
 *
 * @Data:2018/8/8 10:14
 * @Auther:wangxin
 */

@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 15000)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private SendMessage sendMessage;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Goods> getAllGoods() {

        return goodsMapper.selectAllGoods();
    }

    @Override
    public Goods getGoodsById(Integer id) {

        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommonsReturnObject execSeckill(String uid, Integer id, String random) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
        Goods goods = goodsMapper.selectSeckillGoods(id,random);
        if(goods == null){
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("请求参数有误");
            return commonsReturnObject;
        }

        //2、验证秒杀是否开始（因为前面已经验证过，所以者一步是可有可无的）
        //秒杀开始时间
        long startTime = goods.getStarttime().getTime();
        //秒杀结束时间
        long endTime = goods.getEndtime().getTime();
        //当前时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < startTime) {
            //秒杀未开始, 不能返回秒杀唯一标志
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀未开始");
            return commonsReturnObject;

        } else if (nowTime > endTime) {
            //秒杀已结束，不能返回秒杀唯一标志
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀已结束");
            return commonsReturnObject;

        } else {
            //秒杀真正开始了
            //3、验证一下用户是否秒杀过该商品
            //思路：用redis实现，用户秒杀后把redis中设置一个标志，我们基于该标志判断用户是否秒杀过
            String hold = redisTemplate.opsForValue().get(CommonsConstants.REDIS_HOLD + id + ":" + uid);
            if(StringUtils.isNotEmpty(hold)){
                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("您已秒杀过该商品，不能再秒杀了");
                return commonsReturnObject;
            }
            //4、验证一下商品是否已经被抢光了 (库存>0，可以秒杀，否则不能秒杀)
            Integer store = StringUtils.isEmpty(redisTemplate.opsForValue().get(CommonsConstants.REDIS_STORE + id)) ? 0 : Integer.parseInt(redisTemplate.opsForValue().get(CommonsConstants.REDIS_STORE + id));
            if(store <= 0){
                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("商品已卖光~_~");
                System.out.println("卖光了~_~");
                return commonsReturnObject;
            }
            //5、限流（限制访问流量）
            long maxLimit = goods.getStore() * 100;
            long currentLimit = redisTemplate.opsForList().size(CommonsConstants.REDIS_LIMIT + id);
            if(currentLimit >= maxLimit){
                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("系统繁忙，请稍后再试");
                return commonsReturnObject;
            }else {
                System.out.println("放入限流列表.....");
                redisTemplate.opsForList().leftPush(CommonsConstants.REDIS_LIMIT + id ,uid);
            }
            //6、可以正式秒杀
            //1、减库存  2、下单 （需要在数据库中操作，由于秒杀是高并发场景，瞬间流量很大，不要把请求直接落入到数据库）

            //redis中减库存 （库存超卖的问题：锁（数据库的锁，性能不行）（单台部署的，线程锁 syn，性能不行）（分布式锁）、队列 的思路解决）

            //redis是单线程的，你操作redis的时候会排队的，10个同时减库存，那么也是一个一个去减，有先后顺序

            long left = redisTemplate.opsForValue().increment(CommonsConstants.REDIS_STORE + id,-1);

            if(left >= 0){
                //下订单，使用activemq削峰、异步处理
                //给用户一个标识
                redisTemplate.opsForValue().set(CommonsConstants.REDIS_HOLD + id + ":" + uid,uid);
                //发送订单消息
                Orders orders = new Orders();
                orders.setBuynum(1);
                orders.setBuyprice(goods.getPrice());
                orders.setCreatetime(new Date());
                orders.setGoodsid(id);
                orders.setOrdermoney(goods.getPrice().multiply(new BigDecimal(1)));
                //待支付
                orders.setStatus(1);
                orders.setUid(Integer.parseInt(uid));
                String ordersJson = JSONObject.toJSONString(orders);
                sendMessage.sendMessage(ordersJson);

                commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
                commonsReturnObject.setErrorMessage("秒杀成功，正在处理....");
                return commonsReturnObject;
            }else {

                //不能下订单，这个时候库存被减成负数了
                redisTemplate.opsForValue().increment(CommonsConstants.REDIS_STORE + id,1);

                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("商品已卖光~_~");
                return commonsReturnObject;
            }
        }

    }

    /**
     * 创建订单
     * @param orders
     * @return
     */
    @Override
    @Transactional()
    public int createOrders(Orders orders) {
        int insert = ordersMapper.insertSelective(orders);
        if(insert > 0){
            //下单成功
            //秒杀流程执行完了，那么将限流List里面弹出一个元素，以便于后面的用户可以再进来一个
            redisTemplate.opsForList().rightPop(CommonsConstants.REDIS_LIMIT + orders.getGoodsid());
            //整个秒杀流程执行完了，应该让前台页面知道最终的秒杀结果，服务端向客户端推的技术：websocket,
            //通过前台查询后台结果（前台轮询后台，3秒查一次，页面的js定时任务）
            CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
            commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
            commonsReturnObject.setErrorMessage("秒杀成功");
            commonsReturnObject.setData( orders);
            String seckillResult = JSONObject.toJSONString(commonsReturnObject);

            redisTemplate.opsForValue().set(CommonsConstants.REDIS_RESULT + orders.getGoodsid() + ":" + orders.getUid(),seckillResult);

        }else {
            //下单失败
            throw new RuntimeException();
        }
        return insert;
    }

    /**
     * 对异常的处理
     */
    @Override
    public void processException(Orders orders){
        //将删除的库存加进去
        redisTemplate.opsForValue().increment(CommonsConstants.REDIS_STORE + orders.getGoodsid(),1);
        //删除用户标识
        redisTemplate.delete(CommonsConstants.REDIS_HOLD + orders.getGoodsid() + ":" + orders.getUid());
        //秒杀流程执行完了，那么将限流List里面弹出一个元素，以便于后面的用户可以再进来一个
        redisTemplate.opsForList().rightPop(CommonsConstants.REDIS_LIMIT + orders.getGoodsid());
        //给前端结果
        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
        commonsReturnObject.setErrorCode(CommonsConstants.ONE);
        commonsReturnObject.setErrorCode("秒杀失败，请重试");
        String seckillResult = JSONObject.toJSONString(commonsReturnObject);
        redisTemplate.opsForValue().set(CommonsConstants.REDIS_RESULT + orders.getGoodsid() + ":" + orders.getUid(),seckillResult);

    }

    /**
     * 订单查询结果
     * @param uid
     * @param id
     * @return
     */
    @Override
    public CommonsReturnObject queryResult(String uid, Integer id) {
        String result = redisTemplate.opsForValue().get(CommonsConstants.REDIS_RESULT + id + ":" + uid);
        CommonsReturnObject commonsReturnObject = StringUtils.isEmpty(result) ? new CommonsReturnObject() : JSONObject.parseObject(result,CommonsReturnObject.class);
        return commonsReturnObject;
    }

    /**
     * 同步redis中的商品数量到mysql中
     * @param i
     * @param store
     */

    @Override
    public void updateStore(int i, Integer store) {
        Goods goods = new Goods();
        goods.setId(i);
        goods.setStore(store);
        int update = goodsMapper.updateByPrimaryKeySelective(goods);
        if(update > 0){
            System.out.println("同步数据库成功");
        }else {
            System.out.println("同步数据库失败");
        }
    }
}
