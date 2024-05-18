package com.controller;

import com.dto.DeptAndNumsDTO;
import com.github.pagehelper.PageInfo;
import com.pojo.Dept;
import com.pojo.EmpBasic;
import com.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 16:33
 * @Description: com.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/root")
public class DeptController {
    @Autowired
    private DeptService deptService;

    private String classPath;

    @RequestMapping("findByDeptno.action")
    public ModelAndView findDeptByDeptno(Integer deptno,HttpServletRequest request) throws UnsupportedEncodingException {
        if (classPath == null || "".equals(classPath)) {
            String requestUrl = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
            classPath=requestUrl.substring(0, requestUrl.lastIndexOf("/"))+"/";
        }
        String url = classPath+"updateDept.jsp";
        ModelAndView mv=new ModelAndView();
        mv.setViewName("forward:updateDept.jsp");
        Dept dept=deptService.findByDeptno(deptno);
        mv.addObject("Dept",dept);
        return mv;
    }

    @RequestMapping("addDept.action")
    public ModelAndView addDept(Dept dept, HttpServletRequest request) throws UnsupportedEncodingException {
        if (classPath == null || "".equals(classPath)) {
            String requestUrl = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
            classPath=requestUrl.substring(0, requestUrl.lastIndexOf("/"))+"/";
        }
        String url = classPath+"saveDept.html";
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:tips.jsp");
        boolean ifSucess = deptService.addDept(dept);
        mv.addObject("operationResult",ifSucess);
        mv.addObject("href",url);
        return mv;
    }

    @RequestMapping("modifyDept.action")
    public ModelAndView modifyDept(Dept dept,HttpServletRequest request) throws UnsupportedEncodingException {
        if (classPath == null || "".equals(classPath)) {
            String requestUrl = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
            classPath=requestUrl.substring(0, requestUrl.lastIndexOf("/"))+"/";
        }
        String url = classPath+"listDept.html";
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:tips.jsp");
        boolean ifSucess=deptService.modifyDept(dept);
        mv.addObject("operationResult",ifSucess);
        mv.addObject("href",url);
        return mv;
    }

    @RequestMapping("delDept.action")
    public ModelAndView delDept(Integer deptno,HttpServletRequest request) throws UnsupportedEncodingException {
        if (classPath == null || "".equals(classPath)) {
            String requestUrl = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
            classPath=requestUrl.substring(0, requestUrl.lastIndexOf("/"))+"/";
        }
        String url = classPath+"listDept.html";
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:listDept.html");
        boolean ifSucess=deptService.delDept(deptno);
        mv.addObject("operationResult",ifSucess);
        mv.addObject("href",url);

        return mv;
    }

    @ResponseBody
    @RequestMapping("getDeptPage.action")
    public PageInfo<Dept> getDeptPage(int pageNum, int pageSize){
        return deptService.getPageList(pageNum, pageSize);
    }
    @ResponseBody
    @RequestMapping("showAllDept.action")
    public List<Dept> showAllDept(){
        return deptService.showAllDept();
    }

    @ResponseBody
    @RequestMapping("allDeptAndNums.action")
    public List<DeptAndNumsDTO> allDeptAndNums(){
        return deptService.allDeptAndNums();
    }
}
