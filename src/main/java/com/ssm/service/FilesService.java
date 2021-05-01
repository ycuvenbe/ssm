package com.ssm.service;

import com.ssm.po.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ${shuang} on 2017/8/7.
 */
public interface FilesService {
//    上传
    public String upfile(HttpServletRequest request) throws IOException;
//    下载
    public void down(String fileName, HttpServletResponse response);
//     * 根据条件分页查询
    List<Files> selectByFiles(Files files, int page, int rows);

}
