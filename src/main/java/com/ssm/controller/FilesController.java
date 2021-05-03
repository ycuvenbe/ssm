package com.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ssm.Multipledata.DataSourceContextHolder;
import com.ssm.mapper.FilesMapper;
import com.ssm.po.Files;
import com.ssm.po.FilesCustom;
import com.ssm.service.FilesService;
import com.ssm.utiltools.basic.ValueUtil;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by ${shuang} on 2017/8/7.
 */
@RestController
@RequestMapping("/file")
public class FilesController {

    @Autowired
    private FilesService filesService;
    @Autowired
    private FilesMapper filesMapper;


    //   采用spring提供的上传文件的方法
    @RequestMapping("/springUpload")
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        return filesService.upfile(request);
    }

//这是一个测试
//    @RequestMapping(value = {"list", "index", "index.html", ""})
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String getList(@RequestParam Map<String,String> params,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int rows) {
//        ModelAndView result = new ModelAndView(page_list);
        JSONObject result = new JSONObject();
        List<Files> filesList = filesService.selectByFiles(params, page, rows);
        System.out.println(filesList.size());
        result.put("pageInfo", new PageInfo<Files>(filesList));
        result.put("queryParam", params);
        result.put("page", page);
        result.put("rows", rows);
        return ValueUtil.toJson(HttpStatus.SC_OK,result);
    }




    @RequestMapping(value = "/down",method = RequestMethod.GET)
    public void down(Integer id, HttpServletResponse response)  {
        DataSourceContextHolder.setDbType("files");

       FilesCustom files = filesMapper.findByPrimaryKey(id);

        String newpath = "J:"+files.getPath();
        //获得请求文件名
        try {
            // path是指欲下载的文件的路径。
            File file = new File(newpath);
            // 取得文件名。
            String filename =file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(newpath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
//            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}