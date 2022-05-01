package com.huaqi.zhanxin.service;

import cn.hutool.json.JSONObject;

import java.util.List;

public interface MetricsService {

    JSONObject displaySystemInfo();

    void logSystemInfo();

    List<JSONObject> getHistory();
}
