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
 * @Description TODO
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
