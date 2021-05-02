package com.ssm.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.ssm.Multipledata.DataSourceContextHolder;
import com.ssm.mapper.FilesMapper;
import com.ssm.po.Files;
import com.ssm.po.FilesCustom;
import com.ssm.po.FilesQueryVo;
import com.ssm.service.FilesService;
import com.ssm.utiltools.basic.ValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ${shuang} on 2017/8/7.
 */
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesMapper filesMapper;

    @Override
    public String upfile(HttpServletRequest request) throws IOException {
        DataSourceContextHolder.setDbType("files");
        String upPath="J:/test/";
        String newstring =upPath.substring(2);
        long startTime=System.currentTimeMillis();
        //将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path=upPath+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                    FilesCustom files1 = new FilesCustom();
                    files1.setFilename(file.getOriginalFilename());
                    files1.setPath(newstring+file.getOriginalFilename());
                    filesMapper.insertfiles(files1);
                }
            }
        }
        long endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return newstring;
    }

    @Override
    public Map<String,String> down(String fileName, HttpServletResponse response) {

        Map<String,String> map = new HashMap<>();
        // get file list where the path has
        String path = "J://电影种子";
//        String path = "D://迅雷下载";
//        D:\迅雷下载
        File file = new File(path);
        // get the folder list
        File[] array = file.listFiles();
        for(int i=0;i<array.length;i++){
            if(array[i].isFile()){
                map.put(array[i].getName(),array[i].getPath());
//                // only take file name
//                System.out.println("^^^^^" + array[i].getName());
//                // take file path and name
////                System.out.println("#####" + array[i]);
////                // take file path and name
////                System.out.println("*****" + array[i].getPath());
            }else if(array[i].isDirectory()){
                String newpath=array[i].getPath();
                Map<String,String> map2= this.getFile(newpath);
                map.putAll(map2);
            }
        }
        return map;

    }


    public   Map<String,String>  getFile(String path){
        Map<String,String> map = new HashMap<>();
        File file = new File(path);
        // get the folder list
        File[] array = file.listFiles();
        for(int i=0;i<array.length;i++){
            if(array[i].isFile()){
                map.put(array[i].getName(),array[i].getPath());
//                // only take file name
//                System.out.println("^^^^^" + array[i].getName());
//                // take file path and name
////                System.out.println("#####" + array[i]);
////                // take file path and name
////                System.out.println("*****" + array[i].getPath());
            }else if(array[i].isDirectory()){
                String newpath=array[i].getPath();
                Map<String,String> map2 =   this.getFile(newpath);
                map.putAll(map2);
            }
        }
        return map;
    }


    @Override
    public List<Files> selectByFiles(Map<String,String> map, int page, int rows) {
        DataSourceContextHolder.setDbType("files");
        FilesQueryVo filesQueryVo = new FilesQueryVo();
        FilesCustom filesCustom = new FilesCustom();
        filesCustom.setFilename(map.get("fileName"));
        if(ValueUtil.notEmpity(map.get("id"))){
            filesCustom.setId(Integer.valueOf(map.get("id")));
        }
        filesQueryVo.setFilesCustom(filesCustom);
//        FilesExample example = new FilesExample();
//        FilesExample.Criteria criteria = example.createCriteria();
//        if (ValueUtil.notEmpity(map.get("fileName"))) {
//            criteria.andFilenameLike("%"+files.getFilename()+"%");
//        }
////        如果还有条件直接把上面的if复制改字段
//        if (files.getId() != null) {
//            criteria.andIdEqualTo(files.getId());
//        }
        //分页查询
        PageHelper.startPage(page, rows);

        return filesMapper.findListByPage(filesQueryVo);
    }
}
