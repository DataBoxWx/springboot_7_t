package com.wkcto.seckill.task;

import com.wkcto.seckill.commons.CommonsConstants;
import com.wkcto.seckill.model.Goods;
import com.wkcto.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * ClassName:RedisTask
 * package:com.wkcto.seckill.task
 * Description:
 *
 * @Data:2018/8/10 8:59
 * @Auther:wangxin
 */
@Configuration
@EnableScheduling//开启定时任务支持
public class RedisTask {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Scheduled(cron = "0/5 * * * * *")
    public void initStore(){
        List<Goods> goodsList = goodsService.getAllGoods();
        for(Goods goods : goodsList){
            redisTemplate.opsForValue().setIfAbsent(CommonsConstants.REDIS_STORE + goods.getId(),String.valueOf(goods.getStore() )) ;
        }

    }

    @Scheduled(cron = "0/5 * * * * *")
    public void syncStore2Db(){

        Set<String> keySet = redisTemplate.keys(CommonsConstants.REDIS_STORE + "*");
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            Integer store= Integer.parseInt(redisTemplate.opsForValue().get(key));
            String id = key.split(":")[2];
            goodsService.updateStore(Integer.parseInt(id),store);
        }
    }

}
