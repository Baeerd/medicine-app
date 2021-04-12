package com.app.shops.service;

import java.util.List;

import com.app.common.service.BaseService;
import com.app.shops.entity.Shops;
import com.app.shops.entity.ShopsGoodsVo;

public interface ShopsService extends BaseService<Shops>{

	/**
	 * 查找指定数量（最新）的店铺以及店铺商品信息
	 * @param i
	 * @return
	 */
	public List<ShopsGoodsVo> findNewShopsVal(int i);

}
