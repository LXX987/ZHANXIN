package com.huaqi.zhanxin.mapper;


import com.huaqi.zhanxin.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Select("select * from Question where video_id=#{id}")
    List<Question> selectAllByVideoId(@Param("id") Integer id);

    @Insert("insert into Question(video_id,question_text,question_answer) values(#{id},#{text},#{answer})")
    Integer insert(@Param("id") Integer video_id, @Param("text") String question_text, @Param("answer") String question_answer);
}
