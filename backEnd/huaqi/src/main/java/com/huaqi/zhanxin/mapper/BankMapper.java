package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.BankSearch;
import com.huaqi.zhanxin.entity.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BankMapper {
    @Select("SELECT User.user_id, user_name, IDcard \n" +
            "FROM User LEFT JOIN Identity_Info\n" +
            "ON User.user_id=Identity_Info.user_id \n" +
            "Where user_type=0")
    List<BankSearch> bankSearchList();

    @Select("SELECT User.user_id, user_name, IDcard \n" +
            "FROM User LEFT JOIN Identity_Info\n" +
            "ON User.user_id=Identity_Info.user_id \n" +
            "Where user_type=0 AND User.user_id=#{userID}")
    List<BankSearch> bankSearchID(@Param("userID") int userID);

    @Select("SELECT User.user_id, user_name, IDcard \n" +
            "FROM User LEFT JOIN Identity_Info\n" +
            "ON User.user_id=Identity_Info.user_id \n" +
            "Where user_type=0 AND user_name=#{userName}")
    List<BankSearch> bankSearchName(@Param("userName") String userName);
}
