package com.huaqi.zhanxin.mapper;

import cn.hutool.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("select `User`.user_id,user_name,user_email,total_score from Write_Code join Score join `User` on Score.user_id = `User`.user_id and Write_Code.user_id = `User`.user_id where invited_code=(select invite_code from Write_Code where user_id = #{id}) limit ${(pageNum-1)*pageSize},#{pageSize}")
    List<JSONObject> selectAllFriendsById(@Param("id") Integer id, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
