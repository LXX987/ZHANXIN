package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.InviteCode;
import com.huaqi.zhanxin.mapper.InviteCodeMapper;
import com.huaqi.zhanxin.service.InviteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Service
public class InviteCodeServiceImpl implements InviteCodeService {
    @Autowired
    InviteCodeMapper inviteCodeMapper;
    @Override
    public InviteCode searchCode(String inviteCode) {
        return inviteCodeMapper.selectScore(inviteCode);
    }

    @Override
    public InviteCode searchUserCode(int userID) {
        return inviteCodeMapper.selectUserCode(userID);
    }


    @Override
    public int insertCode(int userID, String inviteCode,Timestamp submitTime) {
        return inviteCodeMapper.insertCode(userID,inviteCode,submitTime);
    }

    @Override
    public int insertFriend(int userID, String invitedCode, Timestamp submitTime) {
        return inviteCodeMapper.insertFriend(userID,invitedCode,submitTime);
    }
}
