package com.service;

import com.dto.DeptAndNumsDTO;
import com.github.pagehelper.PageInfo;
import com.pojo.Dept;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 16:29
 * @Description: com.service
 * @version: 1.0
 */
public interface DeptService {
    public boolean addDept(Dept dept);


    public boolean modifyDept(Dept dept);

    public PageInfo<Dept> getPageList(int pageNum, int pageSize);

    public List<Dept> showAllDept();

    public List<DeptAndNumsDTO> allDeptAndNums();

    public boolean delDept(Integer deptno);

    public Dept findByDeptno(Integer deptno);
}
