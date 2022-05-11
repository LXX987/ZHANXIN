package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.Asset;
import com.huaqi.zhanxin.mapper.AssetMapper;
import com.huaqi.zhanxin.mapper.UserMapper;
import com.huaqi.zhanxin.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    AssetMapper assetMapper;

    @Override
    public List<Asset> assetList(int userID) {
        return assetMapper.assetList(userID);
    }

    @Override
    public int insertAsset(int userID, String bank, int money, Timestamp addTime) {
        return assetMapper.insertAsset(userID, bank, money, addTime);
    }

    @Override
    public int getMoney(int userID) {
        return assetMapper.getMoney(userID);
    }

    @Override
    public int deleteAsset(int userID, Timestamp addTime) {
        return assetMapper.deleteAsset(userID,addTime);
    }

    @Override
    public int updateAsset(int userID, String bank, int money, Timestamp addTime) {
        return assetMapper.updateAsset(userID,bank,money,addTime);
    }
}
