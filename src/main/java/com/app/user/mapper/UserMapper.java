package com.app.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.user.entity.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User>{

}
