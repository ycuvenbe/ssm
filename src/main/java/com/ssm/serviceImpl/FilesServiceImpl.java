package com.ssm.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.ssm.Multipledata.DataSourceContextHolder;
import com.ssm.mapper.FilesMapper;
import com.ssm.po.Files;
import com.ssm.po.FilesExample;
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
import java.util.Iterator;
import java.util.List;

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
        String upPath="E:/test/";
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
                    Files files1 = new Files();
                    files1.setFilename(file.getOriginalFilename());
                    filesMapper.insert(files1);
                }
            }
        }
        long endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return upPath;
    }

    @Override
    public void down(String fileName, HttpServletResponse response) {

    }


    @Override
    public List<Files> selectByFiles(Files files, int page, int rows) {
        DataSourceContextHolder.setDbType("files");
        FilesExample example = new FilesExample();
        FilesExample.Criteria criteria = example.createCriteria();
        if (ValueUtil.notEmpity(files.getFilename())) {
            criteria.andFilenameLike("%"+files.getFilename()+"%");
        }
//        如果还有条件直接把上面的if复制改字段
        if (files.getId() != null) {
            criteria.andIdEqualTo(files.getId());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return filesMapper.selectByExample(example);
    }
}