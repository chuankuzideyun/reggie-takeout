package com.reggie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.common.R;
import com.reggie.entity.Category;
import com.reggie.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //新增分类
    @PostMapping
    public R<String> save (@RequestBody Category category){
        log.info("category:{}", category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWapper = new LambdaQueryWrapper<>();
        //添加排序条件，根据sort排序
        queryWapper.orderByAsc(Category::getSort);
        //分页查询
        categoryService.page(pageInfo,queryWapper);
        return R.success(pageInfo);
    }

    //根据id删除
    @DeleteMapping
    public R<String> delete (Long id){
        log.info("删除分类，id为{}", id);

        //categoryService.removeById(id);
        categoryService.remove(id);
        return R.success("分类信息删除成功");
    }

    //根据id修改分类
    public R<String> update(@RequestBody Category category){
        log.info("修改分类信息：{}", category);
        categoryService.updateById(category);
        
        return R.success("修改分类成功");
    }

    //根据条件查询分类数据
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List <Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}
