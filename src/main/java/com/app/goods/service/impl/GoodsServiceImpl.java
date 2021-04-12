package com.app.goods.service.impl;

import com.app.common.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.goods.entity.Goods;
import com.app.goods.mapper.GoodsMapper;
import com.app.goods.service.GoodsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void updateGoodsNum(String goodsId, String numFlag) {
        // 库存-1的时候，校验是否已经没有库存了
        if("-1".equals(numFlag)) {
            Map<String, String> params = new HashMap<>();
            params.put("id", goodsId);
            List<Goods> goodsList = goodsMapper.find(params);
            if(goodsList != null && goodsList.size() > 0) {
                Goods goods = goodsList.get(0);
                if(goods.getNum() < 1) {
                    throw new MessageException("该商品已售罄");
                }
            }
        }
        goodsMapper.updateGoodsNum(goodsId, numFlag);
    }
}
