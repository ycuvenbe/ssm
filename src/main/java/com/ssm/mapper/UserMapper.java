package com.ssm.mapper;

import com.ssm.po.User;
import com.ssm.po.UserCustom;
import com.ssm.po.UserExample;
import com.ssm.po.UserQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUserNameAndPassword(UserCustom userCustom);
    int insertOne(UserCustom UserCustom);

    User findByUserName(String userName);
}