package com.dto;

import com.pojo.EmpDetail;
import com.pojo.Salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: xuzifan
 * @Date: 2024/5/15 - 05 - 15 - 11:19
 * @Description: com.dto
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpAllDTO {
    private Integer empno;
    private String ename;
    private Integer age;
    private String job;
    private Integer deptno;
    private String dname;

    private String gender;
    private String phone;

    private Integer mgr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    private String photo;
    private String description;

    private Double sal;
    private Double comm;


    public EmpAllDTO(EmpBasicDTO empBasicDTO, EmpDetail empDetail, Salary salary) {
        this.empno=empBasicDTO.getEmpno();
        this.ename=empBasicDTO.getEname();
        this.age=empBasicDTO.getAge();
        this.job=empBasicDTO.getJob();
        this.deptno=empBasicDTO.getDeptno();
        this.dname=empBasicDTO.getDname();
        this.gender=empDetail.getGender();
        this.phone=empDetail.getPhone();
        this.mgr=empDetail.getMgr();
        this.hireDate=empDetail.getHireDate();
        this.photo=empDetail.getPhoto();
        this.description=empDetail.getDescription();
        this.sal=salary.getSal();
        this.comm=salary.getComm();
    }
}
