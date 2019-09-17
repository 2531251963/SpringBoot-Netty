package org.easyarch.springboot_netty.service;

import io.netty.handler.codec.http.FullHttpRequest;
import org.easyarch.springboot_netty.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.nio.charset.Charset;

/**
 * @ClassName CentralService
 * @Description 中心Service 从netty channelRead0调用executeServices
 * 通过url分割取除类名在到IOC相应的Service示例 来反射调用业务函数
 * 单独提取 中心Service而不是直接调用某个Service 目地是为了 SpringAOP切面写法
 * 因为SpringAOP无法在带有继承的类中做切面 NettyServerHandler这个类中继承了SimpleChannelInboundHandler
 * 因此无法对channelRead0做函数切面
 * @Author Liyihe
 * @Date 2019/09/16 下午5:08
 * @Version 1.0
 */
@Service
public class CentralService {
    private Logger logger = LoggerFactory.getLogger(CentralService.class);
    public String executeServices(FullHttpRequest request) throws Exception {
        String url = request.uri();
        String data = request.content().toString(Charset.forName("utf-8"));
        String resurl[]=url.split("/");
        Object clazz=BeanUtil.getBean(resurl[1]);
        Method method=clazz.getClass().getMethod(resurl[2],String.class);
        return (String) method.invoke(clazz,data);
    }


}
