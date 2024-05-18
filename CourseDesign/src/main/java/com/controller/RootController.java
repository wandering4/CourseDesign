package com.controller;

import com.pojo.Root;
import com.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 21:34
 * @Description: com.controller
 * @version: 1.0
 */
@Controller
public class RootController {
    @Autowired
    private RootService rootService;
    @RequestMapping("/login.action")
    public ModelAndView login(Root root, HttpSession session){
        ModelAndView mv=new ModelAndView();
        Root user=rootService.find(root);
        if(user!=null){
            session.setAttribute("root",user);
            mv.setViewName("redirect:/root/index.html");
            return mv;
        }
        mv.addObject("loginMessage","用户名或密码错误");
        mv.setViewName("redirect:/login.jsp");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "unameCheck.action")
    public String unameCheck(String uname){
        String info = "";
        if(rootService.findUname(uname)){
            info= "该用户名已存在";
        }
        return info;
    }

    @RequestMapping("/root/logout.action")
    public ModelAndView logout(HttpSession session){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:/login.jsp");
        session.removeAttribute("root");
        return mv;
    }
}
