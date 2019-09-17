package org.easyarch.springboot_netty.service;

import org.easyarch.springboot_netty.mapper.UserMapper;
import org.easyarch.springboot_netty.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BaseServiceImpl
 * @Description 业务接口实现类
 * @Author Liyihe
 * @Date 2019/09/15 下午11:26
 * @Version 1.0
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public String test(String a){
        System.out.println("调用service服务 值"+a);
        User user=new User();
        user.setUserid("123");
        user.setUsername("ly");
        List <User> list=userMapper.findAll(user);
        System.out.println(list);
      //  int a1=3/0;
        return a;
    }
}