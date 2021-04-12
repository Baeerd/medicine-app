package com.app.goods.service;

import com.app.common.service.BaseService;
import com.app.goods.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsService extends BaseService<Goods>{

    /**
     * 增加或减少商品库存
     * @param goodsId
     * @param numFlag
     */
    void updateGoodsNum(String goodsId, String numFlag);
}
