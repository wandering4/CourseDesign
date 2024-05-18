package com.mapper;

import com.pojo.EmpDetail;

/**
 * @Author: xuzifan
 * @Date: 2024/5/11 - 05 - 11 - 9:16
 * @Description: com.mapper
 * @version: 1.0
 */
public interface EmpDetailMapper {
    public boolean addEmpDetail(EmpDetail empDetail);
    public EmpDetail findByEmpno(Integer empno);

    public boolean modifyEmpDetail(EmpDetail empDetail);
}
