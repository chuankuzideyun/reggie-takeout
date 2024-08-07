package com.reggie.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.entity.Dish;
import com.reggie.mapper.DishMapper;
import com.reggie.service.DishService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService{
    
}
