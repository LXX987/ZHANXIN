package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.CreditAppeal;
import com.huaqi.zhanxin.entity.Picture;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}
