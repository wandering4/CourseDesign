package com.service;

import com.pojo.Root;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 21:40
 * @Description: com.service
 * @version: 1.0
 */
public interface RootService {
    public Root find(Root root);

    public boolean findUname(String uname);
}
