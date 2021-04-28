//package com.ssm.test;
//
//import com.ssm.dao.UserDao;
//import com.ssm.mappper.UserMapper;
//import com.ssm.po.User;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * Created by Mars-Hasee on 2017/8/3.
// */
//
//public class testmain {
//
//  ApplicationContext  applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
//
//
//    @Test
//    public void findOneDao() throws Exception {
//        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
//        User user = userDao.findOne(1);
//        System.out.println(user);
//    }
//
//
//    @Test
//    public void findOneMapper() throws Exception {
//        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
//        User user = userMapper.findByUserId(1);
//        System.out.println(user);
//    }
//
//
//}
