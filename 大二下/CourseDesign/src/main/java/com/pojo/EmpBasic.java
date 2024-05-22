package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 16:05
 * @Description: com.pojo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpBasic implements Serializable {
    private Integer empno;
    private String ename;
    private Integer age;
    private String job;
    private Integer deptno;

}
