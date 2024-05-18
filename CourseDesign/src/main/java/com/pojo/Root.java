package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 21:35
 * @Description: com.pojo
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Root implements Serializable {
    String username;
    String password;
    Integer level;
}
