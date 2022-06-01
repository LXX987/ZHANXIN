package com.huaqi.zhanxin.mapper;

import cn.hutool.json.JSONObject;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("select `User`.user_id,user_name,user_email,total_score,user_avatar from Write_Code join Score join `User` on Score.user_id = `User`.user_id and Write_Code.user_id = `User`.user_id where invited_code=(select invite_code from Write_Code where user_id = #{id}) limit ${(pageNum-1)*pageSize},#{pageSize}")
    List<JSONObject> selectAllFriendsById(@Param("id") Integer id, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select invite_code from Write_Code where user_id=#{user_id}")
    String selectOneCodeById(@Param("user_id") Integer id);

    @Update("update Write_Code set invited_code=null where user_id=#{friend_id} and invited_code=#{invite_code}")
    Integer deleteFriend(@Param("friend_id") Integer friend_id, @Param("invite_code") String invite_code);
}
