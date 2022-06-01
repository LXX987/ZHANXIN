package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.Credit;
import com.huaqi.zhanxin.entity.InviteCode;

import java.sql.Timestamp;

public interface InviteCodeService {
    public InviteCode searchCode(String inviteCode);
    public InviteCode searchUserCode(int userID);
    public int insertCode(int userID, String inviteCode,Timestamp submitTime);
    public int insertFriend(int userID, String invitedCode, Timestamp submitTime);

}
