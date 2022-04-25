package com.huaqi.zhanxin.service;

import cn.hutool.json.JSONObject;

import java.util.List;

public interface AccountService {
    List<JSONObject> getAccountList(String type, Integer pageNum, Integer pageSize);

    Integer modifyAccountAuthority(Integer id, Integer authority);

    Integer deactivateAccount(Integer id);
}
