package com.ssm.dao;

import com.ssm.mappper.UserMapper;
import com.ssm.po.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ${shuang} on 2017/8/4.
 */
public class UserDaoTest {

   public  ApplicationContext applicationContext;
    @org.junit.Before
    public void setUp() throws Exception {
         applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void findOne() throws Exception {
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findByUserId(1);
        System.out.println(user);
    }

}