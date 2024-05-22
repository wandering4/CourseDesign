package com.mapper;

import com.pojo.Salary;

/**
 * @Author: xuzifan
 * @Date: 2024/5/11 - 05 - 11 - 9:25
 * @Description: com.mapper
 * @version: 1.0
 */
public interface SalaryMapper {
    public boolean addSalary(Salary salary);
    public Salary findByEmpno(Integer empno);

    public boolean modifySalary(Salary salary);
}
