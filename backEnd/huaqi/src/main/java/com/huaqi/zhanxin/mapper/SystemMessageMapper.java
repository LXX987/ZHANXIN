package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface SystemMessageMapper {
    // 注册向数据库插入数据
    @Insert("insert into System_Message(message_title, message_content, release_time) values (#{messageTitle}, #{messageContent}, #{releaseTime})")
    int releaseMessage(@Param("messageTitle") String messageTitle,@Param("messageContent") String messageContent,@Param("releaseTime") Timestamp releaseTime);

    //查看全部系统信息
    @Select("select * from System_Message")
    List<SystemMessageBean> selectAllMessage();
}
