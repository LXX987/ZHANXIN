package com.huaqi.zhanxin.service;

import cn.hutool.json.JSONObject;

import java.util.List;

public interface FriendService {
    List<JSONObject> getFriendList(Integer id, Integer pageNum, Integer pageSize);

    String getMyCode(Integer id);
}
