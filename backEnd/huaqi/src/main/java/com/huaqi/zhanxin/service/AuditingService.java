package com.huaqi.zhanxin.service;


import cn.hutool.json.JSONObject;

import java.util.List;

public interface AuditingService {
    List<JSONObject> getCertificateList(String type, Integer pageNum, Integer pageSize);

    Integer examineCertificate(Integer id, Integer state, Integer num, String type);
}
