package com.ssm.dao;

import com.ssm.po.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Created by Mars-Hasee on 2017/8/3.
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements  UserDao {

    public User findOne(Integer id) throws Exception {
        //通过继承获取sqlSession
        SqlSession sqlSession = this.getSqlSession();
        User user = sqlSession.selectOne("test.findByUserId" ,id);
        return  user;
    }
}



