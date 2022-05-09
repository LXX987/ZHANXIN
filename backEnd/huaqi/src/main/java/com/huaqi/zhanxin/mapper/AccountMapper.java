package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.UserBean;
import com.huaqi.zhanxin.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("select * from User where user_type=#{type} limit ${(pageNum-1)*pageSize},#{pageSize}")
    List<UserBean> selectAllByState(@Param("type") String type, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from User where user_type=#{type}")
    Integer selectUserCountByState(@Param("type") String type);

    @Update("update User set user_type=#{type} where user_id=#{id}")
    Integer updateTypeById(@Param("id") Integer id, @Param("type") Integer type);

    @Delete("delete from User where user_id=#{id}")
    Integer deleteById(@Param("id") Integer id);
}
