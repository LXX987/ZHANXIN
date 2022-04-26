package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.PsychologyBean;
import com.huaqi.zhanxin.mapper.PsychologyMapper;
import com.huaqi.zhanxin.service.PsychologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PsychologyServiceImpl implements PsychologyService {
    @Autowired
    PsychologyMapper psychologyMapper;
    PsychologyBean psychologyBean;

    @Override
    public List<PsychologyBean> psychologyList(){
        return psychologyMapper.psychologyList();
    }

    @Override
    public List<PsychologyBean> getHistory(int userId){
        return psychologyMapper.psychologyHistoryList(userId);
    }

    @Override
    public List<PsychologyBean> getResult(int userId){
        return psychologyMapper.psychologyHistoryList(userId);
    }

    @Override
    public int recordScore(int userId, Timestamp testTime, int score1, int score2,int score3, int score4, int score5, int score){
        //计算分数
        return  psychologyMapper.recordScore(userId,testTime,score,score1,score2,score3,score4,score5);
    }
}
