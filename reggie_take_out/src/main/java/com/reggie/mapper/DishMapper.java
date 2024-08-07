package com.reggie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.entity.Dish;

@Mapper
public interface DishMapper extends BaseMapper<Dish>{
    
}
