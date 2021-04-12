package com.app.goods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.goods.entity.Goods;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods>{

    /**
     * 增加或减少商品库存
     * @param goodsId
     * @param numFlag
     */
    void updateGoodsNum(@Param("goodsId") String goodsId, @Param("numFlag") String numFlag);
}
