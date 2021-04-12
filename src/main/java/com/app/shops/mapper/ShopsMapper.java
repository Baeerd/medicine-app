package com.app.shops.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.shops.entity.Shops;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopsMapper extends BaseMapper<Shops>{

}
