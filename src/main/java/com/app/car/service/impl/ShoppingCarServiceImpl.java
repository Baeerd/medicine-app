package com.app.car.service.impl;

import com.app.common.exception.MessageException;
import com.app.common.util.LoginUtil;
import com.app.goods.entity.Goods;
import com.app.goods.mapper.GoodsMapper;
import com.app.order.entity.GoodsOrder;
import com.app.order.service.GoodsOrderService;
import com.github.pagehelper.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.car.entity.ShoppingCar;
import com.app.car.mapper.ShoppingCarMapper;
import com.app.car.service.ShoppingCarService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCarServiceImpl extends BaseServiceImpl<ShoppingCar> implements ShoppingCarService {

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsOrderService goodsOrderService;
    
    /**
     * 逻辑：
     * 1 判断当前登录人是否已经有购物车
     * 2 如果没有，则新增购物车并将商品添加进去（同时商品库存-1）
     * 3 如果已经存在购物车，判断购物车中是否有此商品
     * 4 如果没有此商品，则将商品添加至此购物车（同时商品库存-1）
     * 5 如果有此商品，则将商品的数量+1（同时商品库存-1）
     * @param params
     */
    @Override
    public void addGoods(Map<String, String> params) {
        String goodsId = params.get("goodsId");
        // 判断商品库存是否已经存在
        Map<String, String> goodsParams = new HashMap<>();
        goodsParams.put("id", goodsId);
        List<Goods> goodsList = goodsMapper.find(goodsParams);
        if(goodsList != null && goodsList.size() > 0) {
            Goods goods = goodsList.get(0);
            if(goods.getNum() < 1) {
                throw new MessageException("该商品已售罄");
            }
        }
        // 判断当前登录人是否已经有购物车
        Map<String, String> carParams = new HashMap<>();
        carParams.put("userId", LoginUtil.getUserId());
        List<ShoppingCar> userCarList = shoppingCarMapper.find(carParams);
        if(userCarList == null || userCarList.size() == 0) {
            // 如果没有，则新增购物车并将商品添加进去
            addGoodsToShoppingCar(goodsId);
        } else {
            // 如果已经存在购物车，判断购物车中是否有此商品
            carParams.put("goodsId", goodsId);
            List<ShoppingCar> userGoodsCarList = shoppingCarMapper.find(carParams);
            // 如果没有此商品，则将商品添加至此购物车
            if(userGoodsCarList == null || userGoodsCarList.size() == 0) {
                addGoodsToShoppingCar(goodsId);
            } else {
                // 如果有此商品，则将商品的数量+1
                ShoppingCar shoppingCar = userGoodsCarList.get(0);
                shoppingCarMapper.updateCarNum(shoppingCar.getId().toString(), "+1");
                // 同时商品库存-1
                goodsMapper.updateGoodsNum(goodsId, "-1");
            }
        }
    }

    @Override
    public void updateCarNum(String id, String numFlag) {
        shoppingCarMapper.updateCarNum(id, numFlag);
    }

    /**
     * 将商品添加至当前登录人的购物车
     * @param goodsId
     */
    private void addGoodsToShoppingCar(String goodsId) {
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setCreatedDt(new Date());
        shoppingCar.setCreatedBy(LoginUtil.getUserName());
        shoppingCar.setGoodsId(Long.valueOf(goodsId));
        shoppingCar.setNum(1);
        shoppingCar.setUserId(Long.valueOf(LoginUtil.getUserId()));
        shoppingCarMapper.insert(shoppingCar);

        // 同时商品库存-1
        goodsMapper.updateGoodsNum(goodsId, "-1");
    }

    /**
     * 清空购物车：删除购物车数据的同时，将购物车中商品数量加到商品库存中
     * @param userId
     */
    @Override
    public void deleteByUser(String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        List<ShoppingCar> shoppingCarList = shoppingCarMapper.find(params);
        if(shoppingCarList != null && shoppingCarList.size() > 0) {
            for (ShoppingCar shoppingCar : shoppingCarList) {
                Integer num = shoppingCar.getNum();
                // 将购物车中商品数量还到商品库存中
                goodsMapper.updateGoodsNum(shoppingCar.getGoodsId().toString(), "+"+num);
                // 将购物车中的该项删除
                shoppingCarMapper.delete(shoppingCar);
            }
        }
    }

	/**
	 * 提交当前人的所有购物车信息至订单，并清空购物车
	 * 设定：一个商家的所有商品为一个订单号（多个商家为多个订单号）
	 */
	public void addOrderByUser(String userId) {
		
		//提交至订单
		Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        List<ShoppingCar> shoppingCarList = shoppingCarMapper.find(params);
        if(shoppingCarList != null && shoppingCarList.size() > 0) {
        	//定义一个map用于存储商家的订单号信息
        	Map<Long,String> orderNoMap = new HashMap<>();
        	int i = 1;
            for (ShoppingCar shoppingCar : shoppingCarList) {
            	//创建订单实体,赋值存储
            	GoodsOrder goodsOrder = new GoodsOrder();
            	
            	//购物车的商品信息
            	Goods goods = shoppingCar.getGoods();
            	Long shopsId = goods.getShopsId();
            	//通过商铺ID判断是否此商铺是否已有订单号（当前时间加流水号）
            	String orderNo = orderNoMap.get(shopsId);
            	if(StringUtil.isEmpty(orderNo)) {
            		//无订单号,创建订单号,并将订单号放置orderNoMap中
            		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            		orderNo = sdf.format(new Date())+i;
            		i++;
            		orderNoMap.put(shopsId, orderNo);
            	}
            	goodsOrder.setCreatedBy(LoginUtil.getUserName());
            	goodsOrder.setGoods(goods);
            	goodsOrder.setGoodsId(shoppingCar.getGoodsId());
            	goodsOrder.setNum(shoppingCar.getNum());
            	goodsOrder.setOrderNo(orderNo);
            	goodsOrder.setOrderType("0");//待付款状态
            	goodsOrder.setUserId(shoppingCar.getUserId());
            	Double totalPrice = new Double(0);
            	totalPrice = goods.getPrice()*shoppingCar.getNum();
            	goodsOrder.setTotalPrice(totalPrice);
            	
            	goodsOrderService.insert(goodsOrder);
            }
        }
		
        //回调清除购物车方法
        this.deleteByUser(userId);
	}

}
