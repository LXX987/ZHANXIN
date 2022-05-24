package com.huaqi.zhanxin.service.impl;

import com.huaqi.zhanxin.entity.PsychologyBean;
import com.huaqi.zhanxin.entity.SystemMessageBean;
import com.huaqi.zhanxin.mapper.PsychologyMapper;
import com.huaqi.zhanxin.mapper.SystemMessageMapper;
import com.huaqi.zhanxin.service.SystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SystemMessageServiceImpl implements SystemMessageService {

    @Autowired
    SystemMessageMapper systemMessageMapper;
    SystemMessageBean systemMessageBean;

    @Override
    public int insetSystemMessage(String messageTitle, String messageContent, Timestamp testTime){
        return systemMessageMapper.releaseMessage(messageTitle, messageContent, testTime);
    }

    @Override
    public List<SystemMessageBean> getMessage() {
        return systemMessageMapper.selectAllMessage();
    }
}
