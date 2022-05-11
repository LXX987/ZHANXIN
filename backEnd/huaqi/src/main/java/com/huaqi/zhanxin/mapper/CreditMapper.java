package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.Credit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CreditMapper {
    @Select("select * from Score where user_id=#{userID}")
    Credit selectScore(@Param("userID") int userID);

    @Insert("insert into Score(user_id,total_score,identity_score,asset_score,credit_score,behavior_score,social_score) values (#{user_id},#{total_score},#{identity_score},#{asset_score},#{credit_score},#{behavior_score},#{social_score})")
    int insertInfo(@Param("user_id") int user_id,@Param("total_score") int total_score,@Param("identity_score") int identity_score,@Param("asset_score") int asset_score,@Param("credit_score") int credit_score,@Param("behavior_score") int behavior_score,@Param("social_score") int social_score);

    @Update("update Score set total_score = #{total_score}")
    int updateTotalScore(@Param("total_score") int total_score);

    @Update("update Score set identity_score = #{identity_score}")
    int updateIdentityScore(@Param("total_score") int identity_score);

    @Update("update Score set asset_score = #{asset_score}")
    int updateAssetScore(@Param("asset_score") int asset_score);

    @Update("update Score set behavior_score = #{behavior_score}")
    int updateBehaviorScore(@Param("behavior_score") int behavior_score);

    @Update("update Score set social_score = #{social_score}")
    int updateSocialScore(@Param("social_score") int social_score);

    @Update("update Score set credit_score = #{credit_score}")
    int updateCreditScore(@Param("credit_score") int credit_score);
}
