package com.ssm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ${shuang} on 2017/8/7.
 */
public interface FileService {
//    上传
    public String upfile(HttpServletRequest request) throws IOException;
//    下载
    public void down(String fileName, HttpServletResponse response);
}
