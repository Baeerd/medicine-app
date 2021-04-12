package com.app.shops.entity;

import java.util.List;

import com.app.common.entity.AbstractEntity;
import com.app.goods.entity.Goods;

public class ShopsGoodsVo extends AbstractEntity{

    private Shops shops;
    
    private List<Goods> goodsList;

	public Shops getShops() {
		return shops;
	}

	public void setShops(Shops shops) {
		this.shops = shops;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
    
}
