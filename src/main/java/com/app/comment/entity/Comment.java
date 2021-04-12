package com.app.comment.entity;

import com.app.common.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

public class Comment extends AbstractEntity{

    private String content;

    private Long goodsId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

}
