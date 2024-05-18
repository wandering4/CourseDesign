package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 16:05
 * @Description: com.pojo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {
    private Integer deptno;
    private String dname;
    private String loc;
    private String description;
}
