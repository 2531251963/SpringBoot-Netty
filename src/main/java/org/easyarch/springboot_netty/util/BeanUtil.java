package org.easyarch.springboot_netty.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName BeanUtil
 * @Description 自定义Bean工具类
 * @Author Liyihe
 * @Date 2019/09/16 下午5:24
 * @Version 1.0
 */

public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (BeanUtil.applicationContext == null) {
            BeanUtil.applicationContext = applicationContext;
        }
     /*   String s[]=applicationContext.getBeanDefinitionNames();
        for (String a :
                s) {
            System.out.println(a);
        }*/
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name从IOC获取 Bean.
    public static Object getBean(String name) {

        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}

