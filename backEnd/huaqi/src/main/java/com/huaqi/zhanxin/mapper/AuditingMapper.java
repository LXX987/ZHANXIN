package com.huaqi.zhanxin.mapper;


import com.huaqi.zhanxin.entity.AssetVerification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AuditingMapper {

    @Select("select * from Asset_Verification where certificate=#{type} limit ${(pageNum-1)*pageSize},#{pageSize}")
    List<AssetVerification> selectAllByType(@Param("type") String type, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Update("update Asset_Verification set state=#{state} where certicficate_id=#{id}")
    Integer updateStateById(@Param("id") Integer id, @Param("state") Integer state);

    @Select("select user_id from Asset_Verification where certicficate_id=#{id}")
    Integer selectUserIdById(@Param("id") Integer id);

    @Update("update Reputation set ${type}=${type}+#{num} where user_id=#{user_id}")
    void updateReputation(@Param("user_id") Integer user_id, @Param("num") Integer num, @Param("type") String type);
}
