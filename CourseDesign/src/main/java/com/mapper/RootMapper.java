package com.mapper;

import com.pojo.Root;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 21:38
 * @Description: com.mapper
 * @version: 1.0
 */
public interface RootMapper {
    public Root find(Root root);

    public int findUname(String uname);
}
