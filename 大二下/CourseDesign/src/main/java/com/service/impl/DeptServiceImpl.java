package com.service.impl;

import com.dto.DeptAndNumsDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.DeptMapper;
import com.pojo.Dept;
import com.pojo.EmpBasic;
import com.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 16:29
 * @Description: com.service.impl
 * @version: 1.0
 */
@Transactional
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }

    @Override
    public boolean modifyDept(Dept dept) {
        return deptMapper.modifyDept(dept);
    }

    @Override
    public PageInfo<Dept> getPageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Dept> depts = deptMapper.getAllDept();

        return  new PageInfo<>(depts);
    }

    @Override
    public List<Dept> showAllDept() {
        return deptMapper.getAllDept();
    }

    @Override
    public List<DeptAndNumsDTO> allDeptAndNums() {
        return deptMapper.allDeptAndNums();
    }

    @Override
    public boolean delDept(Integer deptno) {
        return deptMapper.delByDeptno(deptno);
    }

    @Override
    public Dept findByDeptno(Integer deptno) {
        return deptMapper.findByDeptno(deptno);
    }
}
