package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.InviteCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    @Insert("insert into Write_Code(user_id,invite_code) values (#{userID},#{inviteCode})")
    int insertCode(@Param("userID") int userID,@Param("inviteCode") String inviteCode);

    // 插入好友邀请码和提交时间
    @Insert("insert into Write_Code(user_id,invited_code,submit_time) values (#{userID},#{invitedCode},#{submitTime})")
    int insertFriend(@Param("userID") int userID,@Param("invitedCode") String invitedCode,@Param("submitTime") Timestamp submitTime);

}
