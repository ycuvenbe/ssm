package com.ssm.dao;


import com.ssm.po.User;

/**
 * Created by ${shuang} on 2017/7/20.
 */
public interface UserDao {

     User findOne(Integer id) throws Exception;
}
