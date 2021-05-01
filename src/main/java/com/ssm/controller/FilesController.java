package com.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ssm.po.Files;
import com.ssm.service.FilesService;
import com.ssm.utiltools.basic.ValueUtil;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by ${shuang} on 2017/8/7.
 */
@RestController
@RequestMapping("/file")
public class FilesController {

    @Autowired
    private FilesService filesService;

    private String page_list = "index";

    private String redirect_list = "redirect:list";

    //   采用spring提供的上传文件的方法
    @RequestMapping("/springUpload")
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        return filesService.upfile(request);
    }


//    @RequestMapping(value = {"list", "index", "index.html", ""})
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String getList(Files files,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int rows) {
//        ModelAndView result = new ModelAndView(page_list);
        JSONObject result = new JSONObject();
        List<Files> filesList = filesService.selectByFiles(files, page, rows);
        result.put("pageInfo", new PageInfo<Files>(filesList));
        result.put("queryParam", files);
        result.put("page", page);
        result.put("rows", rows);
        return ValueUtil.toJson(HttpStatus.SC_OK,result);
    }


}