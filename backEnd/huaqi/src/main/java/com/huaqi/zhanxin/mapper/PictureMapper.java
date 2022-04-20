package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureMapper {
    @Insert("insert into Asset_Verification(user_id,state,certificate,file_name) values (#{userID},#{state},#{picType},#{fileName})")
    int fileInsert(@Param("userID") int userID, @Param("state") int state, @Param("picType") String picType, @Param("fileName") String fileName);

    @Select("select sum(certicficate_id) from Asset_Verification")
    Integer getCurNumber();

    @Select("select * from Asset_Verification where user_id =#{userId} and file_name = 'house' ")
    Picture getHouse(@Param("userId") int userId);

    // 获取未审核信息列表
    @Select("select * from Asset_Verification where state=0")
    List<Picture> pictureList();
}
