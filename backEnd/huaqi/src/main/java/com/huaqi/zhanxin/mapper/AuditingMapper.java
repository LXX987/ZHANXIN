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
}
