package com.huaqi.zhanxin.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineSessionListener implements HttpSessionListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static Integer concurrent_user_count = 0;
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent)
    {
        logger.info("新用户上线了");
        concurrent_user_count++;
        httpSessionEvent.getSession().getServletContext().setAttribute("count", concurrent_user_count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
    {
        logger.info("用户下线了");
        concurrent_user_count--;
        httpSessionEvent.getSession().getServletContext().setAttribute("count", concurrent_user_count);
    }
}