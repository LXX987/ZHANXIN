package com.huaqi.zhanxin.service.impl;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.entity.AssetVerification;
import com.huaqi.zhanxin.mapper.AuditingMapper;
import com.huaqi.zhanxin.service.AuditingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuditingServiceImpl implements AuditingService {
    @Resource
    AuditingMapper auditingMapper;

    @Override
    public List<JSONObject> getCertificateList(String type, Integer pageNum, Integer pageSize)
    {
        List<JSONObject> jsonObjects = new ArrayList<>();
        List<AssetVerification> assetVerificationList = auditingMapper.selectAllByType(type, pageNum, pageSize);
        if(assetVerificationList.isEmpty())
            return null;
        for (var assetVerification:assetVerificationList) {
            JSONObject jsonObject = new JSONObject(assetVerification);
            jsonObjects.add(jsonObject);
        }
        return jsonObjects;
    }
}
