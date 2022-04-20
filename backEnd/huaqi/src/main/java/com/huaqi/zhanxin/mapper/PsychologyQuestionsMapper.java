package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.PsychologyQuestionsBean;
import com.huaqi.zhanxin.entity.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PsychologyQuestionsMapper {

    @Select("select * from Psychology_Test")
    List<PsychologyQuestionsBean> psychologyQuestionsList();

}
