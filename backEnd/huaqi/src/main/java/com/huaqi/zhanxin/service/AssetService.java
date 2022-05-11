package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.Asset;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface AssetService {
    List<Asset> assetList(int userID);
    int insertAsset(int userID, String bank, int money, Timestamp addTime);
    int getMoney(int userID);
    int deleteAsset(int userID,Timestamp addTime);
    int updateAsset(int userID, String bank, int money, Timestamp addTime);
}
