package com.app.car.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.car.entity.ShoppingCar;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShoppingCarMapper extends BaseMapper<ShoppingCar>{

    /**
     * 增加或减少购物车商品数量
     * @param id
     * @param numFlag
     */
    void updateCarNum(@Param("id") String id, @Param("numFlag") String numFlag);
}
