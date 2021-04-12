package com.app.order.entity;

import com.app.common.entity.AbstractEntity;
import com.app.common.entity.Constant;
import com.app.goods.entity.Goods;

public class GoodsOrder extends AbstractEntity{

    private Double totalPrice;

    private String orderNo;

    private Long userId;

    private Long goodsId;

    private Goods goods;

    private Integer num;

    private String orderType;
    
    private String orderTypeView;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
        this.orderTypeView = Constant.dataConfigMap.get(Constant.ORDER_TYPE).get(orderType);
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

	public String getOrderTypeView() {
		return orderTypeView;
	}
    
    
}
