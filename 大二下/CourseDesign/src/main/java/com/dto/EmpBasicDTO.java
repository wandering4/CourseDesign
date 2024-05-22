package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xuzifan
 * @Date: 2024/5/15 - 05 - 15 - 10:28
 * @Description: com.dto
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpBasicDTO {
    private Integer empno;
    private String ename;
    private Integer age;
    private String job;
    private Integer deptno;
    private String dname;
}
