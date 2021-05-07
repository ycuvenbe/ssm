package com.ssm.service;

import com.ssm.utiltools.error.ToolsException;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangdiandian on 2017/5/16.
 */
public interface EmailService{

//    String updateSave(Map<String, String> param) throws yesmywineException;
    String send(Map<String, String> param, HttpServletRequest request) throws ToolsException;
}