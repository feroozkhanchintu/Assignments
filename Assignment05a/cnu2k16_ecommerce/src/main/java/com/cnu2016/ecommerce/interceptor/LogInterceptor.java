package com.cnu2016.ecommerce.interceptor;

import com.cnu2016.ecommerce.pojo.LogPOJO;
import com.cnu2016.ecommerce.sqs.SimpleQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vipulj on 11/07/16.
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    //after the handler is executed

    @Autowired
    private SimpleQueueService simpleQueueService;

    public LogInterceptor() {}

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {

        Long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        return true;
    }

    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {

        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        Long endTime = System.currentTimeMillis();
        //TODO : enter start time
        LogPOJO logPOJO = new LogPOJO(new Date(), request.getRequestURI(), map.toString() ,
                response.getStatus(), request.getRemoteAddr(), endTime - (Long)request.getAttribute("startTime"), request.getMethod());

        simpleQueueService.pushToQueue(logPOJO);
    }
}
