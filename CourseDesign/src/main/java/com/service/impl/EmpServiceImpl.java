package com.service.impl;

import com.dto.EmpAllDTO;
import com.dto.EmpBasicDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.EmpDetailMapper;
import com.mapper.EmpBasicMapper;
import com.mapper.SalaryMapper;
import com.pojo.EmpBasic;
import com.pojo.EmpDetail;
import com.pojo.Salary;
import com.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 16:39
 * @Description: com.service.impl
 * @version: 1.0
 */
@Transactional
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpBasicMapper empBasicMapper;
    @Autowired
    private EmpDetailMapper empDetailMapper;
    @Autowired
    private SalaryMapper salaryMapper;


    @Override
    public List<EmpAllDTO> getDocument() {
        List<EmpAllDTO> datas=new ArrayList<>();
        List<EmpBasicDTO> empBasicDTOS = empBasicMapper.showAllEmpBasicDTO();
        EmpDetail empDetail=null;
        Salary salary=null;
        for (int i = 0; i < empBasicDTOS.size(); i++) {
            Integer empno = empBasicDTOS.get(i).getEmpno();
            empDetail = empDetailMapper.findByEmpno(empno);
            salary=salaryMapper.findByEmpno(empno);
            datas.add(new EmpAllDTO(empBasicDTOS.get(i),empDetail,salary));
        }
        return datas;
    }

    @Override
    public boolean addEmp(EmpBasic empBasic, EmpDetail empDetail, Salary salary) {
        if(salary.getComm()==null){
            salary.setComm(0.0);
        }

        boolean flag=empBasicMapper.addEmpBasic(empBasic);
        empDetail.setEmpno(empBasic.getEmpno());
        salary.setEmpno(empBasic.getEmpno());
        return flag
                && empDetailMapper.addEmpDetail(empDetail)
                && salaryMapper.addSalary(salary);
    }

    @Override
    public Map<String, Object> findByEmpno(Integer empno) {
        Map<String,Object> map=new HashMap<>();
        EmpBasic empBasic = empBasicMapper.findByEmpno(empno);
        EmpDetail empDetail = empDetailMapper.findByEmpno(empno);
        Salary salary = salaryMapper.findByEmpno(empno);
        map.put("EmpBasic",empBasic);
        map.put("EmpDetail",empDetail);
        map.put("Salary",salary);
        return map;
    }

    @Override
    public boolean deleteByEmpno(Integer empno) {
        return empBasicMapper.delEmp(empno);
    }

    @Override
    public PageInfo<EmpBasicDTO> getPageList(int pageNum, int pageSize, String ename, String job) {

        PageHelper.startPage(pageNum,pageSize);
        List<EmpBasicDTO> empBasicDTOs ;
        if (ename != null && !ename.isEmpty() || job != null && !job.isEmpty()) {
            // 如果 ename 和 job 有一个不为空，则添加约束条件
            empBasicDTOs = empBasicMapper.showAllEmpBasicDTOWithConstraint(ename, job);
        } else {
            // 如果 ename 和 job 有一个为空，则不添加约束条件
            empBasicDTOs = empBasicMapper.showAllEmpBasicDTO();
        }
        return  new PageInfo<>(empBasicDTOs);
    }

    @Override
    public List<EmpBasic> getAllEmpBasic() {
        return empBasicMapper.showAllEmpBasic();
    }

    @Override
    public boolean modifyEmp(EmpBasic empBasic,EmpDetail empDetail,Salary salary) {
        return empBasicMapper.modifyEmpBasic(empBasic) &&
                empDetailMapper.modifyEmpDetail(empDetail) &&
                salaryMapper.modifySalary(salary);
    }

}
