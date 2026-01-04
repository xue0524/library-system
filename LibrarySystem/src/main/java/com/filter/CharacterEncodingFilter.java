package com.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

// 注解映射所有请求，替代web.xml配置
@WebFilter(urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法，留空即可
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 设置请求和响应的编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 继续执行后续过滤器/请求
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 销毁方法，留空即可
    }
}
