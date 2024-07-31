package com.reggie.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reggie.entity.Employee;
import com.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.reggie.common.R;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login (HttpServletRequest request, @RequestBody Employee employee){
        
        //加密password
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        
        //根据username查数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        
        //没查到则返回登陆失败
        if (emp==null){
            return R.error("Fail to login");
        }
        
        //比对密码，不一致则返回登陆失败
        if (!emp.getPassword().equals(password)){
            return R.error("Fail to login");
        }
        
        //查看账户状态，已禁用则返回已禁用
        if (emp.getStatus() == 0){
            return R.error("Account Disable");
        }
        
        //登陆成功
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    //log out
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("Logout successfully");
    };

    //新员工
    @PostMapping
    public R<String> save(@RequestBody Employee employee){
        log.info("新员工，信息{}", employee.toString());
        return null;
    }
}
