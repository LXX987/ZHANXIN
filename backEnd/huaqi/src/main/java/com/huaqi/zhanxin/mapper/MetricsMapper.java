package com.huaqi.zhanxin.mapper;


import cn.hutool.json.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MetricsMapper {

    @Insert("insert into Metrics(cpu_core,cpu_usage,memory_size,memory_usage,concurrent_user,log_time) values(#{core},#{cpu_usage},#{mem_size},#{mem_usage},#{concurrent_user},#{log_time})")
    void logSystemInfo(@Param("core") Integer core, @Param("cpu_usage")String cpu_usage, @Param("mem_size")String mem_size, @Param("mem_usage")String mem_usage, @Param("concurrent_user")Integer concurrent_user, @Param("log_time")LocalDateTime log_time);

    @Select("select * from Metrics")
    List<JSONObject> getHistory();

}
