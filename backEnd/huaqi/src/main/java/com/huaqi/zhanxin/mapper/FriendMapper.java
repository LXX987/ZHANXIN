package com.huaqi.zhanxin.mapper;

import cn.hutool.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("select friend_id,user_name,user_email,score from Social_Relationship join User on User.user_id=Social_Relationship.friend_id where Social_Relationship.user_id=#{id} limit ${(pageNum-1)*pageSize},#{pageSize}")
    List<JSONObject> selectAllFriendsById(@Param("id") Integer id, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
