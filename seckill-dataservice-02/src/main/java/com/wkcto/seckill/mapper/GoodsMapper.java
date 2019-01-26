package com.wkcto.seckill.mapper;

import com.wkcto.seckill.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    /**
     * 查询所有秒杀商品
     * @return
     */
    List<Goods> selectAllGoods();

    /**
     * 根据唯一标识和商品Idh获取商品信息
     * @param id
     * @param random
     * @return
     */
    Goods selectSeckillGoods(@Param("id") Integer id,@Param("random") String random);
}