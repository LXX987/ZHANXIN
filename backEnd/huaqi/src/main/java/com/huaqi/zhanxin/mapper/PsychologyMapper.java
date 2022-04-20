package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.PsychologyBean;
import com.huaqi.zhanxin.entity.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface PsychologyMapper {
    @Select("select * from Psychology")
    List<PsychologyBean> psychologyList();

    @Select("select * from Psychology where user_id=#{userId}")
    List<PsychologyBean> psychologyHistoryList(@Param("userId") int userId);

    @Insert("insert into Psychology(user_id,test_time,test_score,openness,conscientiousness,extraversion,agreeableness,neuroticism)"+
            "values(#{user_id},#{test_time},#{test_score},#{openness},#{conscientiousness},#{extraversion},#{agreeableness},#{neuroticism})")
    int recordScore(@Param("user_id") int userId, @Param("test_time") Timestamp testTime, @Param("test_score") int testScore, @Param("openness") int openness, @Param("conscientiousness") int conscientiousness, @Param("extraversion") int extraversion, @Param("agreeableness") int agreeableness, @Param("neuroticism") int neuroticism);

}
