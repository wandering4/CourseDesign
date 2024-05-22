package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xuzifan
 * @Date: 2024/5/12 - 05 - 12 - 21:57
 * @Description: com.dto
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptAndNumsDTO {
    private String dname;
    private Integer empNums;
}
