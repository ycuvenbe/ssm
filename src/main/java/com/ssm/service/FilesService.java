package com.ssm.service;

import com.ssm.po.Files;
import com.ssm.po.FilesCustom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ${shuang} on 2017/8/7.
 */
public interface FilesService {
//    上传
     String upfile(HttpServletRequest request) throws IOException;
//    下载
     Map<String,String> down(String fileName, HttpServletResponse response);
//     * 根据条件分页查询
    List<Files> selectByFiles(Map<String,String> map, int page, int rows);

}
