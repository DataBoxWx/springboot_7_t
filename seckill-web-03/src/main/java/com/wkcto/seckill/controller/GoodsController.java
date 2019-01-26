package com.wkcto.seckill.controller;

import com.alibaba.dubbo.common.threadpool.support.fixed.FixedThreadPool;
import com.alibaba.dubbo.config.annotation.Reference;
import com.wkcto.seckill.commons.CommonsConstants;
import com.wkcto.seckill.commons.CommonsReturnObject;
import com.wkcto.seckill.model.Goods;
import com.wkcto.seckill.model.User;
import com.wkcto.seckill.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:GoodsController
 * package:com.wkcto.seckill.controller
 * Description:
 *
 * @Data:2018/8/8 10:34
 * @Auther:wangxin
 */
@Controller
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    /**
     * 秒杀商品详情页
     * @param model
     * @return
     */
    @GetMapping("/goods/getAll")
    public String getGoods(Model model,HttpSession session){
        User user = new User();
        user.setId("1111");
        session.setAttribute(CommonsConstants.SESSION_USER,user);
        List<Goods> goodsList = goodsService.getAllGoods();
        model.addAttribute("goodsList",goodsList);
        return "index";
    }

    @GetMapping("/seckill/goods/{id}")
    public String getGoodsById(@PathVariable("id") Integer id,Model model){
        Goods goods = goodsService.getGoodsById(id);
        model.addAttribute("goods",goods);
        model.addAttribute("nowTime",System.currentTimeMillis());
        return "detail";
    }
    //获取秒杀唯一标识
    @PostMapping("/seckill/goods/random/{id}")
    public @ResponseBody CommonsReturnObject getRandom(@PathVariable("id") Integer id){
        Goods goods = goodsService.getGoodsById(id);
        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
        //判断什么情况下可以给前台返回商品秒杀唯一标志(秒杀真正开始了，才可以给前台返回秒杀唯一标志)
        long nowTime = System.currentTimeMillis();
        long startTime = goods.getStarttime().getTime();
        long endTime = goods.getEndtime().getTime();

        if(nowTime < startTime){
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀还未开始");
        }else if(nowTime > endTime){
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀已结束");
        }else {
            commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
            commonsReturnObject.setErrorMessage("秒杀已开始");
            //秒杀唯一标识
            commonsReturnObject.setData(goods.getRandomname());
        }

        return commonsReturnObject;
    }


    /**
     * 执行秒杀操作
     */
    @PostMapping("/seckill/goods/{id}/{random}")
    public @ResponseBody CommonsReturnObject execSeckill(@PathVariable("id")Integer id,
                                                         @PathVariable("random") String random,
                                                         HttpSession session){
        User user = (User) session.getAttribute(CommonsConstants.SESSION_USER);

        //模拟
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        for(int i = 0; i < 10000000; i++){
            String uid = String.valueOf(i);
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    goodsService.execSeckill(uid,id,random);
                }
            });
        }

        
        CommonsReturnObject commonsReturnObject = goodsService.execSeckill(user.getId(),id,random);
        return commonsReturnObject;
    }
    /**
     * 查询结果
     */
    @PostMapping("/seckill/result/{id}")
    public @ResponseBody CommonsReturnObject queryResult(@PathVariable("id") Integer id,HttpSession session){
        User user = (User) session.getAttribute(CommonsConstants.SESSION_USER);
        return goodsService.queryResult(user.getId(),id);
    }
}
