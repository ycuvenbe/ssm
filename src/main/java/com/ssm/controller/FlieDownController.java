//package com.ssm.controller;
//
//import com.ssm.service.FilesService;
//import com.ssm.utiltools.basic.ValueUtil;
//import com.ssm.utiltools.error.ToolsException;
//import org.apache.http.HttpStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by Mars-Hasee on 2017/7/30.
// */
//@RestController
//@Controller
//public class FlieDownController {
//
//    @Autowired
//    private FilesService FilesService;
//
//    @RequestMapping(value = "/index",method = RequestMethod.POST)
//    public Object index(String fileName, Integer pageSize, Integer pageNumber){
///*
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        if(ValueUtil.isEmpity(pageSize)){
//            pageSize=10;
//        }if(ValueUtil.isEmpity(pageNumber)){
//            pageNumber=0;
//        }
//        Pageable pageable = new PageRequest(pageNumber, pageSize, sort);
//        String [] status= statusType.split(",");
//        List<String> list= new ArrayList<>();
//        for (int i = 0; i <status.length ; i++) {
//            list.add(status[i]);
//        }
//        Page<BeanFlow> pages=beanFlowDao.findByStatusIn(list,pageable);
//        return pages;*/
//        return null;
//    }
//
//    @RequestMapping(value = "/showfile",method = RequestMethod.POST)
//    public  String show(){
//        Map<String,String> map =null;
//        List<Object> list = new ArrayList<>();
//        try {
//            map =  fileDownService.getFileName();
//        } catch (ToolsException e) {
//            e.printStackTrace();
//        }
//        Set set = map.keySet();
//        for(Object key : set){
//            list.add(key);
//        }
//        return ValueUtil.toJson(HttpStatus.SC_OK,list);
//    }
//
//    @RequestMapping(value = "/down",method = RequestMethod.GET)
//    public void down(String fileName, HttpServletResponse response)  {
//        System.out.println(fileName);
//
//        Map<String,String> map =  fileDownService;
//
//        String path = map.get(fileName);
//
//        try {
//            // path????????????????????????????????????
//            File file = new File(path);
//            // ??????????????????
//            String filename = file.getName();
//            // ???????????????????????????
//            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
//            // ??????????????????????????????
//            InputStream fis = new BufferedInputStream(new FileInputStream(path));
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            fis.close();
//            // ??????response
//            response.reset();
//            // ??????response???Header
//            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
////            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
//            response.addHeader("Content-Length", "" + file.length());
//            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/octet-stream");
//            toClient.write(buffer);
//            toClient.flush();
//            toClient.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//}
