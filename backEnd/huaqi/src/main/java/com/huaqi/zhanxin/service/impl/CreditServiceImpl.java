package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.Credit;
import com.huaqi.zhanxin.mapper.CreditMapper;
import com.huaqi.zhanxin.mapper.UserMapper;
import com.huaqi.zhanxin.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    CreditMapper creditMapper;
    // 获取用户信用分数
    @Override
    public Credit selectScore(int userID){return creditMapper.selectScore(userID);}
}
