package com.interceptor;

import com.pojo.Root;
import com.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @Author: xuzifan
 * @Date: 2024/5/17 - 05 - 17 - 9:33
 * @Description: com.interceptor
 * @version: 1.0
 */
public class DeleteInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Root root = (Root) session.getAttribute("root");
        if(root==null){
            response.sendRedirect("/login.jsp");
            return false;
        }
        if(root.getLevel()!=1){
            String referer = request.getHeader("referer");
            if (referer != null && !referer.isEmpty()) {
                // URL encode the parameter value
                String encodedAlert = URLEncoder.encode("您没有相关权限!", "UTF-8");
                // Redirect back to the original page with the parameter
                response.sendRedirect(referer + "?alert=" + encodedAlert);
            } else {
                // If referer is not available, redirect to a default page
                response.sendRedirect("/login.jsp");
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
