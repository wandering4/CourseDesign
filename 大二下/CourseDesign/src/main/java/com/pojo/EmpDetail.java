package com.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: xuzifan
 * @Date: 2024/5/9 - 05 - 09 - 17:21
 * @Description: com.pojo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDetail implements Serializable {
    private Integer empno;
    private String gender;
    private String phone;

    private Integer mgr;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    private String photo;
    private String description;

}
