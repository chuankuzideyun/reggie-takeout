package com.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.dto.DishDto;
import com.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品，插入口味数据，操作dish+dish_flavor两张表
    public void saveWithFlavor (DishDto dishDto);
    
}
