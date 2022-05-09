package com.huaqi.zhanxin.service.impl;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.mapper.FriendMapper;
import com.huaqi.zhanxin.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    FriendMapper friendMapper;

    @Override
    public List<JSONObject> getFriendList(Integer id, Integer pageNum, Integer pageSize)
    {
        List<JSONObject> jsonObjects = friendMapper.selectAllFriendsById(id, pageNum, pageSize);
        if(jsonObjects.isEmpty())
            return null;
        return jsonObjects;
    }

    @Override
    public String getMyCode(Integer id)
    {
        return friendMapper.selectOneCodeById(id);
    }
}
