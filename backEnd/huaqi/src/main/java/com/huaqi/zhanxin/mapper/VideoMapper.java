package com.huaqi.zhanxin.mapper;


import com.huaqi.zhanxin.entity.VideoInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface VideoMapper {
    @Select("select * from Video_Info where video_type=#{video_type}")
    List<VideoInfo> selectAllByType(@Param("video_type") String type);

    @Insert("insert into Video_Info(video_name,video_time,video_url,video_intro,video_cover,video_type)" +
            " values (#{video_name},#{video_time},#{video_url},#{video_intro},#{video_cover},#{video_type})")
    void insert(@Param("video_name")String video_name, @Param("video_time")LocalDateTime video_time, @Param("video_url")String video_url,
                @Param("video_intro")String video_intro, @Param("video_type") String video_type, @Param("video_cover") String video_cover);

    @Delete("delete from Video_Info where video_id=#{id}")
    Integer deleteOneById(Integer id);
}
