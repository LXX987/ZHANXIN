package com.huaqi.zhanxin.mapper;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.entity.CreditAppeal;
import com.huaqi.zhanxin.entity.Picture;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface CreditAppealMapper {

    @Insert("insert into Credit_Appeal values(#{userID},#{appealTime},#{reason},#{question},#{require},#{appealState})")
    int insertAppeal(@Param("userID") int userID,@Param("appealTime") Timestamp appealTime,@Param("reason") String reason,@Param("question") String question,@Param("require") String require,@Param("appealState") int appealState);

    @Select("select * from Credit_Appeal where user_id =#{userId}")
    List<CreditAppeal> getCreditAppeal(@Param("userId") int userId);

    @Select("select * from Credit_Appeal limit ${(pageNum-1)*pageSize},#{pageSize}")
    List<JSONObject> selectAll(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select * from Credit_Appeal where reason=#{type} limit ${(pageNum-1)*pageSize},#{pageSize}")
    List<JSONObject> selectAllByType(@Param("type") String type, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Update("update Credit_Appeal set appeal_state=2 where user_id=#{id} and appeal_time=#{time}")
    Integer updateAppealStateToDenied(@Param("id") Integer user_id, @Param("time") Timestamp appeal_time);

    @Update({"update Credit_Appeal set appeal_state=1 where user_id=#{id} and appeal_time=#{time}; update ${type} set ${detail}=#{value} where user_id=#{id}"})
    Integer updateAppealStateToAccepted(@Param("id") Integer user_id, @Param("time") Timestamp appeal_time, @Param("type") String type, @Param("detail") String detail, @Param("value") Double value);
}
