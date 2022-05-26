package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface SystemMessageService {
    public int insetSystemMessage(String messageTitle, String messageContent, Timestamp testTime);

    public List<SystemMessageBean> getMessage();
}
