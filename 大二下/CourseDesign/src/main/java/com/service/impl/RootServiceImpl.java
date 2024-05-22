package com.service.impl;

import com.mapper.RootMapper;
import com.pojo.Root;
import com.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: xuzifan
 * @Date: 2024/5/6 - 05 - 06 - 21:41
 * @Description: com.service.impl
 * @version: 1.0
 */
@Transactional
@Service
public class RootServiceImpl implements RootService {
    @Autowired
    private RootMapper rootMapper;

    @Override
    public boolean findUname(String uname) {
        return rootMapper.findUname(uname)==1;
    }

    @Override
    public Root find(Root root) {
        return rootMapper.find(root);
    }
}
