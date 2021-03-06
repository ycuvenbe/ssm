package com.ssm.mapper;

import com.ssm.po.Files;
import com.ssm.po.FilesCustom;
import com.ssm.po.FilesExample;
import com.ssm.po.FilesQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesMapper {
    int countByExample(FilesExample example);

    int deleteByExample(FilesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Files record);

    int insertfiles(FilesCustom record);

    int insertSelective(Files record);

    List<Files> selectByExample(FilesExample example);

    Files selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Files record, @Param("example") FilesExample example);

    int updateByExample(@Param("record") Files record, @Param("example") FilesExample example);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);

    List<Files> findListByPage(FilesQueryVo filesQueryVo);
    FilesCustom findByPrimaryKey(Integer id);

}