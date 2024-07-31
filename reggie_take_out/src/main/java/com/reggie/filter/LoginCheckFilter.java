package com.reggie.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;

import com.alibaba.fastjson.JSON;
import com.reggie.common.R;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter{
    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获得请求uri
        String requestURI = request.getRequestURI();
        log.info("拦截到请求{}", requestURI);
        //不需要处理的
        String [] urls = new String[]{
            "/employee/logout",
            "/employee/login",
            "/backend/**",
            "/front/**"
        };

        //是否需要处理该请求-->检查登陆状态
        boolean check = check(urls, requestURI);

        //不需要处理的情况，直接放行
        if(check){
            log.info("{}不需要处理",requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        //需要处理，完成登录
        if (request.getSession().getAttribute("employee")!= null){
            log.info("已登录，id为{}",request.getSession().getAttribute("employee"));
            filterChain.doFilter(request, response);
            return;
        }
        log.info("未登录");
        //如果未登录则需要登陆，通过输出流的方式响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));


        log.info("拦截到请求：{}",request.getRequestURI());
        filterChain.doFilter(request, response);
    }

    public boolean check(String[] urls, String requestURI){
        for(String url: urls){
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;

    }
}
