package com.ssm.controller;


import com.ssm.service.EmailService;
import com.ssm.utiltools.basic.ValueUtil;
import com.ssm.utiltools.error.ToolsException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Created by wangdiandian on 2017/5/16.
 */
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

   /* @RequestMapping(method = RequestMethod.PUT)
    public String updateSave(@RequestParam Map<String,String> param){//修改保存邮件账号信息
        try {
            return ValueUtil.toJson(HttpStatus.SC_CREATED,emailService.updateSave(param));
        }catch (yesmywineException e){
            return ValueUtil.toError(e.getCode(),e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show() {//显示邮件账号信息
        return ValueUtil.toJson(emailService.findAll().get(0));
    }
*/

    @RequestMapping(value = "/send/overt",method = RequestMethod.POST)
    public String create(@RequestParam Map<String,String> param, HttpServletRequest request){//电子邮件发送
        try {
            return ValueUtil.toJson(HttpStatus.SC_CREATED,emailService.send(param,request));
        }catch (ToolsException e){
            return ValueUtil.toError(e.getCode(),e.getMessage());
        }
    }

}
