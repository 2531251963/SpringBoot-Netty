package org.easyarch.springboot_netty.util;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.FullHttpRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.easyarch.springboot_netty.pojo.ReturnApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @ClassName AopTestController
 * @Description TODO
 * @Author Liyihe
 * @Date 2019/09/16 下午4:31
 * @Version 1.0
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    JwtUtil jwtUtil;
    @Pointcut(value = "execution(* org.easyarch.springboot_netty.service.CentralService.executeServices(..))")
    public void cutOffPoint() { }
    @Around("cutOffPoint()")
    public Object around(ProceedingJoinPoint pjp){
        FullHttpRequest request=(FullHttpRequest)pjp.getArgs()[0];
        String authorization=request.headers().get("Authorization");
        String userid=null;
        if (authorization!=null)userid=jwtUtil.getUserIdFromJWT(authorization);
        if (userid==null) {
            ReturnApi returnApi=new ReturnApi(Const.HTTPCODE400,Const.BADREQUEST,null);
            return JSON.toJSONString(returnApi);
        }
        String url=request.uri();
        String data=request.content().toString(Charset.forName("utf-8"));
        Object responseData=null;
        long start=System.currentTimeMillis();
        try {
            responseData=pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            ReturnApi returnApi=new ReturnApi(Const.HTTPCODE500,Const.ERROR,null);
            responseData=JSON.toJSONString(returnApi);
        }
        long end=System.currentTimeMillis()-start;
        logger.info("time:{}ms\tdata:{}\turl:{}\tres:{}",end,data,url,responseData);
        return responseData;
    }
}
