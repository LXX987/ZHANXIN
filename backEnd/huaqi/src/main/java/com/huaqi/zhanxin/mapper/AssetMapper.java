package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.Asset;
import com.huaqi.zhanxin.entity.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface AssetMapper {

    @Select("select * from Asset where user_id=#{userID}")
    List<Asset> assetList(@Param("userID") int userID);

    @Insert("insert into Asset(user_id,bank,money,add_time) values (#{userID},#{bank},#{money},#{addTime})")
    int insertAsset(@Param("userID") int userID,@Param("bank") String bank,@Param("money") int money,@Param("addTime") Timestamp addTime);

    @Select("select sum(money) from Asset where user_id=#{userID}")
    int getMoney(@Param("userID") int userID);

    @Delete("delete from Asset where user_id = #{userID} and add_time = #{addTime}")
    int deleteAsset(@Param("userID") int userID,@Param("addTime") Timestamp addTime);

    @Update("update Asset set bank = #{bank},money = #{money} where user_id = #{userID} and add_time = #{addTime}")
    int updateAsset(@Param("userID") int userID,@Param("bank") String bank,@Param("money") int money,@Param("addTime") Timestamp addTime);
}
