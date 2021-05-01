package com.ssm.controller;

import com.ssm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by ${shuang} on 2017/8/7.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    //   采用spring提供的上传文件的方法
    @RequestMapping("/springUpload")
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        return fileService.upfile(request);
    }

}