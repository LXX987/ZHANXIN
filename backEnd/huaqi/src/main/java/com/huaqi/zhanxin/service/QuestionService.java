package com.huaqi.zhanxin.service;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.entity.VideoScore;

import java.util.List;

public interface QuestionService {

    List<JSONObject> getQuestionList(Integer video_id);

    Integer addQuestion(Integer video_id, String question_text, String question_answer);

    Integer submitQuestion(Integer userID, Integer video_id,Integer watch,Integer questionScore);

    List<VideoScore> getVideoScore(Integer userId);
}
