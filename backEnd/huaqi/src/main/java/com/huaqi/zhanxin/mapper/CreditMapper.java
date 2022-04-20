package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.Credit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CreditMapper {
    @Select("select * from Score where user_id=#{userID}")
    Credit selectScore(@Param("userID") int userID);



}
