package com.filter;

import com.pojo.Root;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: xuzifan
 * @Date: 2024/5/8 - 05 - 08 - 21:15
 * @Description: com.filter
 * @version: 1.0
 */
@Component
@WebFilter(urlPatterns = "/root/*")
public class Filter01_LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Root root = (Root)request.getSession().getAttribute("root");
        if(root!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
