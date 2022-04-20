package com.huaqi.zhanxin.service.impl;


import com.huaqi.zhanxin.entity.PsychologyBean;
import com.huaqi.zhanxin.entity.PsychologyQuestionsBean;
import com.huaqi.zhanxin.mapper.PsychologyQuestionsMapper;
import com.huaqi.zhanxin.service.PsychologyQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsychologyQuestionsServiceImpl implements PsychologyQuestionsService {
    @Autowired
    PsychologyQuestionsMapper psychologyQuestionsMapper;

    @Override
    public List<PsychologyQuestionsBean> psychologyQuestionsList(){
        return psychologyQuestionsMapper.psychologyQuestionsList();
    }
}
