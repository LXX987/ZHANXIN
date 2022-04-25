package com.huaqi.zhanxin.mapper;


import com.huaqi.zhanxin.entity.AssetVerification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuditingMapper {

    @Select("select * from Asset_Verification where file_name=#{type} limit ${(pageNum-1)*pageSize},#{pageSize}")
    List<AssetVerification> selectAllByType(@Param("type") String type, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
