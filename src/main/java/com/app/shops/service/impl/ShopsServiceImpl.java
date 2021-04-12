package com.app.shops.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.entity.PageModel;
import com.app.common.service.impl.BaseServiceImpl;
import com.app.goods.entity.Goods;
import com.app.goods.service.GoodsService;
import com.app.shops.entity.Shops;
import com.app.shops.entity.ShopsGoodsVo;
import com.app.shops.mapper.ShopsMapper;
import com.app.shops.service.ShopsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopsServiceImpl extends BaseServiceImpl<Shops> implements ShopsService {

	@Autowired
	private GoodsService goodsService;
	
	@Override
	public List<ShopsGoodsVo> findNewShopsVal(int i) {
		List<ShopsGoodsVo> sgvList = new ArrayList<>();
		Map<String,String> params = new HashMap<>();
		params.put("pageNum", "1");
		params.put("pageSize", i+"");
		PageModel<Shops> shopsPage = this.findByPage(params);
		List<Shops> shopsList = shopsPage.getList();
		for (Shops shops : shopsList) {
			ShopsGoodsVo shopsGoodsVo = new ShopsGoodsVo();
			shopsGoodsVo.setShops(shops);
			Map<String,String> paramsMap = new HashMap<>();
			paramsMap.put("shopsId", shops.getId().toString());
			List<Goods> goodsList = goodsService.findByParam(paramsMap);
			shopsGoodsVo.setGoodsList(goodsList);
			sgvList.add(shopsGoodsVo);
		}
		return sgvList;
	}

}
