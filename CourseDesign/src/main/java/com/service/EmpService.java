package com.service;

import com.dto.EmpAllDTO;
import com.dto.EmpBasicDTO;
import com.github.pagehelper.PageInfo;
import com.pojo.EmpBasic;
import com.pojo.EmpDetail;
import com.pojo.Salary;

import java.util.List;
import java.util.Map;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 16:39
 * @Description: com.service
 * @version: 1.0
 */
public interface EmpService {
    public boolean addEmp(EmpBasic empBasic, EmpDetail empDetail, Salary salary);

    public boolean deleteByEmpno(Integer empno);

    public Map<String,Object> findByEmpno(Integer empno);

    /**
     * 获取分页数据
     * @param pageNum 当前页码
     * @param pageSize 每页条数
     * @return
     */
    public PageInfo<EmpBasicDTO> getPageList(int pageNum, int pageSize, String ename, String job);

    public List<EmpBasic> getAllEmpBasic();

    public boolean modifyEmp(EmpBasic empBasic,EmpDetail empDetail,Salary salary);

    public List<EmpAllDTO> getDocument();
}
