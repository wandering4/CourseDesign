package com.mapper;

import com.dto.EmpBasicDTO;
import com.pojo.EmpBasic;
import com.pojo.EmpDetail;
import com.pojo.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 16:38
 * @Description: com.mapper
 * @version: 1.0
 */
public interface EmpBasicMapper {
    public boolean addEmpBasic(EmpBasic empBasic);

    public EmpBasic findByEmpno(Integer empno);

    public List<EmpBasic> showAllEmpBasic();

    public List<EmpBasicDTO> showAllEmpBasicDTO();

    public boolean delEmp(Integer empno);

    List<EmpBasicDTO> showAllEmpBasicDTOWithConstraint(@Param("ename") String ename, @Param("job") String job);


    public boolean modifyEmpBasic(EmpBasic empBasic);
}
