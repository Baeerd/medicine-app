package com.app.order.entity;

import java.util.List;

import com.app.common.entity.AbstractEntity;
import com.app.goods.entity.Goods;

public class GoodsOrderVo extends AbstractEntity{

	private String creatDt;
	
    private String orderNo;
    
    private String shopsName;
    
    private Double totalPrice;
    
    private String showPrice;
    
    private String orderTypeView;
    
    private List<Goods> goodsList;

    
	public String getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(String showPrice) {
		this.showPrice = showPrice;
	}

	public String getOrderTypeView() {
		return orderTypeView;
	}

	public void setOrderTypeView(String orderTypeView) {
		this.orderTypeView = orderTypeView;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getCreatDt() {
		return creatDt;
	}

	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}
	
	
}
