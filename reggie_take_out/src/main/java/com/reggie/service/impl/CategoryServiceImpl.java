package com.reggie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.common.CustomException;
import com.reggie.entity.Category;
import com.reggie.entity.Dish;
import com.reggie.entity.Setmeal;
import com.reggie.mapper.CategoryMapper;
import com.reggie.service.CategoryService;
import com.reggie.service.DishService;
import com.reggie.service.SetmealService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{
    @Autowired
    private DishService disheService;

    @Autowired
    private SetmealService setmealService;

    //根据id删除类
    @Override
    public void remove(Long id){
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //是否关联菜品
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = disheService.count(dishLambdaQueryWrapper);
        if(count1>0){
            //已经关联
            throw new CustomException("当前分类已关联菜品，不能删除");
        }

        //是否关联套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count();
        if(count2>0){
            throw new CustomException("当前分类已关联套餐，不能删除");
        }

        //删除
        super.removeById(id);
    }
}
