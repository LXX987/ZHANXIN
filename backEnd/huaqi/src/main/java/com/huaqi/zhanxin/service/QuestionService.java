package com.huaqi.zhanxin.service;

import cn.hutool.json.JSONObject;

import java.util.List;

public interface QuestionService {

    List<JSONObject> getQuestionList(Integer video_id);

    Integer addQuestion(Integer video_id, String question_text, String question_answer);

}
