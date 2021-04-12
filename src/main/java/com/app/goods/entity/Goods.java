package com.app.goods.entity;

import com.app.common.entity.AbstractEntity;
import com.app.common.entity.Constant;
import com.app.common.util.Util;

import java.math.BigDecimal;
import java.util.Date;

public class Goods extends AbstractEntity{

    private String name;

    private String remark;

    private Integer num;

    private Double price;

    private String image;

    private String goodsType;

    private Long shopsId;

    private Date createdDt;

    private String createdDtView;

    private String goodsTypeView;

    private String shopsIdView;
    
    private Integer orderGoodsNum;//订单中此商品的数量 仅用于展示

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public Long getShopsId() {
        return shopsId;
    }

    @Override
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
        this.createdDtView = Util.formatDate(createdDt);
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
        this.goodsTypeView = Constant.dataConfigMap.get(Constant.GOODS_TYPE).get(goodsType);
    }

    public void setShopsId(Long shopsId) {
        this.shopsId = shopsId;
        this.shopsIdView = Constant.dataConfigMap.get(Constant.SHOPS).get(shopsId+"");
    }

    public String getCreatedDtView() {
        return createdDtView;
    }

    public String getGoodsTypeView() {
        return goodsTypeView;
    }

    public String getShopsIdView() {
        return shopsIdView;
    }

	public Integer getOrderGoodsNum() {
		return orderGoodsNum;
	}

	public void setOrderGoodsNum(Integer orderGoodsNum) {
		this.orderGoodsNum = orderGoodsNum;
	}
    
    
}
