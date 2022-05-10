package com.huaqi.zhanxin.service.impl;

import cn.hutool.json.JSONObject;
import com.huaqi.zhanxin.entity.Question;
import com.huaqi.zhanxin.entity.VideoScore;
import com.huaqi.zhanxin.mapper.QuestionMapper;
import com.huaqi.zhanxin.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    QuestionMapper questionMapper;

    public List<JSONObject> getQuestionList(Integer video_id)
    {
        List<JSONObject> jsonObjects = new ArrayList<>();
        List<Question> questionList = questionMapper.selectAllByVideoId(video_id);
        if(questionList.isEmpty())
            return null;
        for (var question:questionList) {
            JSONObject jsonObject = new JSONObject(question);
            jsonObjects.add(jsonObject);
        }
        return jsonObjects;
    }

    public Integer addQuestion(Integer video_id, String question_text, String question_answer)
    {
        Integer count = questionMapper.insert(video_id, question_text, question_answer);
        if(count == 1)
            return 1;
        else return -1;
    }

    public Integer submitQuestion(Integer userID, Integer video_id, Integer watch, Integer questionScore) {
        Integer count = questionMapper.insertAnswer(userID,video_id,watch,questionScore);
        if(count == 1)
            return 1;
        else return -1;
    }

    @Override
    public List<VideoScore> getVideoScore(Integer userId) {
        List<VideoScore> scoreList = questionMapper.selectAllByUserId(userId);
        if(scoreList.isEmpty()) return null;
        return scoreList;
    }

}
