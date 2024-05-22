package com.mapper;

import com.dto.DeptAndNumsDTO;
import com.pojo.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 16:23
 * @Description: com.mapper
 * @version: 1.0
 */
public interface DeptMapper {
    public boolean addDept(Dept dept);

    public List<Dept> getAllDept();

    public boolean modifyDept(Dept dept);

    public List<DeptAndNumsDTO> allDeptAndNums();

    public boolean delByDeptno(Integer deptno);

    public Dept findByDeptno(@Param("deptno") Integer deptno);
}
