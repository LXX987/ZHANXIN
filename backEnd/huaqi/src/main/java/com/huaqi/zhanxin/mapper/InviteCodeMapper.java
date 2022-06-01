package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.InviteCode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
@Mapper
@Repository
public interface InviteCodeMapper {
    // 查找该邀请码是否存在
    @Select("select * from Write_Code where invite_code=#{inviteCode}")
    InviteCode selectScore(@Param("inviteCode") String inviteCode);

    // 查找某个用户的邀请码
    @Select("select * from Write_Code where user_id=#{userID}")
    InviteCode selectUserCode(@Param("userID")int userID);

    // 插入邀请码
    @Insert("insert into Write_Code(user_id,invite_code,submit_time) values (#{userID},#{inviteCode},#{submitTime})")
    int insertCode(@Param("userID") int userID,@Param("inviteCode") String inviteCode,@Param("submitTime") Timestamp submitTime);

    // 插入好友邀请码和提交时间
    @Update("update Write_Code set invited_code=#{invitedCode},submit_time=#{submitTime} where user_id=#{userID}")
    int insertFriend(@Param("userID") int userID,@Param("invitedCode") String invitedCode,@Param("submitTime") Timestamp submitTime);

}
