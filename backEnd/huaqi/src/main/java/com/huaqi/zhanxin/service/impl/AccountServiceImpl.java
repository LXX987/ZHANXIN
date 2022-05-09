package com.huaqi.zhanxin.service.impl;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.entity.UserBean;
import com.huaqi.zhanxin.entity.UserInfo;
import com.huaqi.zhanxin.mapper.AccountMapper;
import com.huaqi.zhanxin.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountMapper accountMapper;

    @Override
    public List<JSONObject> getAccountList(String type, Integer pageNum, Integer pageSize)
    {
        List<JSONObject> jsonObjects = new ArrayList<>();
        List<UserBean> userInfoList = accountMapper.selectAllByState(type, pageNum, pageSize);
        if(userInfoList.isEmpty())
            return null;
        for (var userInfo:userInfoList) {
            JSONObject jsonObject = new JSONObject(userInfo);
            jsonObjects.add(jsonObject);
        }
        JSONObject userCount = new JSONObject();
        userCount.put("count", accountMapper.selectUserCountByState(type));
        jsonObjects.add(userCount);
        return jsonObjects;
    }

    @Override
    public Integer modifyAccountAuthority(Integer id, Integer authority)
    {
        Integer count = accountMapper.updateTypeById(id, authority);
        if(count == 1)
            return 1;
        else return -1;
    }

    @Override
    public Integer deactivateAccount(Integer id)
    {
        Integer count = accountMapper.deleteById(id);
        if(count == 1)
            return 1;
        else return -1;
    }
}
