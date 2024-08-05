package com.reggie.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.entity.Category;

@Mapper
public interface CatagoryMapping extends BaseMapper<Category>{
    
}
