package com.reggie.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.entity.Employee;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{

}
