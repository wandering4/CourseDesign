package com.controller;

import com.dto.EmpBasicDTO;
import com.github.pagehelper.PageInfo;
import com.pojo.EmpBasic;
import com.pojo.EmpDetail;
import com.pojo.Salary;
import com.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 17:58
 * @Description: com.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/root")
public class EmpController {
    @Autowired
    private EmpService empService;

    private String classPath;

    @RequestMapping("AddEmp.action")
    public ModelAndView AddEmp(EmpBasic empBasic, EmpDetail empDetail, Salary salary, HttpServletRequest request) throws UnsupportedEncodingException {
        if (classPath == null || "".equals(classPath)) {
            String requestUrl = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
            classPath=requestUrl.substring(0, requestUrl.lastIndexOf("/"))+"/";
        }
        String url = classPath+ "saveEmp.html";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:tips.jsp");
        boolean ifSuccess = empService.addEmp(empBasic, empDetail, salary);
        mv.addObject("operationResult", ifSuccess);
        mv.addObject("href", url);
        return mv;
    }



    @ResponseBody
    @RequestMapping("showEmp.action")
    public Map<String,Object> showEmpDetail(Integer empno){
        return empService.findByEmpno(empno);
    }

    @ResponseBody
    @RequestMapping("getEmpPage.action")
    public PageInfo<EmpBasicDTO> getEmpPage(int pageNum,int pageSize,String ename,String job){
        return empService.getPageList(pageNum, pageSize,ename,job);
    }

    @ResponseBody
    @RequestMapping("showAllEmpBasic.action")
    public List<EmpBasic> getAllEmpBasic(HttpServletRequest request){
        return empService.getAllEmpBasic();
    }

    @RequestMapping("deleteByEmpno.action")
    public ModelAndView deleteByEmpno(Integer empno,HttpServletRequest request) throws UnsupportedEncodingException {
        if (classPath == null || "".equals(classPath)) {
            String requestUrl = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
            classPath=requestUrl.substring(0, requestUrl.lastIndexOf("/"))+"/";
        }
        String url=classPath+"listEmp.html";
        boolean ifSuccess = empService.deleteByEmpno(empno);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:listEmp.html");
        mv.addObject("operationResult", ifSuccess);
        mv.addObject("href", url);
        return mv;
    }

    @RequestMapping("findByEmpno.action")
    public ModelAndView findByEmpno(Integer empno,HttpServletRequest request)throws UnsupportedEncodingException {
        if (classPath == null || "".equals(classPath)) {
            String requestUrl = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
            classPath=requestUrl.substring(0, requestUrl.lastIndexOf("/"))+"/";
        }
        Map<String,Object> Emp=empService.findByEmpno(empno);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:updateEmp.jsp");
        mv.addAllObjects(Emp);
        return mv;
    }

    @RequestMapping("modifyEmp.action")
    public ModelAndView modifyEmp(Integer empno, String ename, Integer age, String job, Integer deptno,
                                  String gender, String phone, Integer mgr, @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate, String description, String photo,
                                  Double sal, Double comm,
                                  HttpServletRequest request) throws UnsupportedEncodingException {
        if (classPath == null || "".equals(classPath)) {
            String requestUrl = URLDecoder.decode(request.getRequestURL().toString(), "UTF-8");
            classPath=requestUrl.substring(0, requestUrl.lastIndexOf("/"))+"/";
        }

        EmpBasic empBasic=new EmpBasic(empno,ename,age,job,deptno);
        EmpDetail empDetail=new EmpDetail(empno,gender,phone,mgr,hireDate,photo,description);
        Salary salary=new Salary(empno,sal,comm);


        String url=classPath+"listEmp.html";
        boolean ifSuccess = empService.modifyEmp(empBasic,empDetail,salary);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:tips.jsp");
        mv.addObject("operationResult", ifSuccess);
        mv.addObject("href", url);
        return mv;
    }
}
