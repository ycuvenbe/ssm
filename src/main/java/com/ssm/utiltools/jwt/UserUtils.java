package com.ssm.utiltools.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.utiltools.basic.Statement;
import com.ssm.utiltools.basic.ValueUtil;
import com.ssm.utiltools.error.ToolsException;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shuang on 2017/6/8.
 */
public class UserUtils {

    public static Integer getUserId(HttpServletRequest request) throws ToolsException {
        JSONObject userJson = getUserJson(request);
        if(userJson==null){
            return null;
        }
        return userJson.getInteger("id");
    }

    public static Integer ifLoginGetId(HttpServletRequest request) throws ToolsException {
        JSONObject userJson = getUserJson(request);
        if(userJson==null){
            ValueUtil.isError("登录超时，或用户未登录");
        }
        return userJson.getInteger("id");
    }

    public static String getUserName(HttpServletRequest request) throws ToolsException {
        JSONObject userJson = getUserJson(request);
        if(userJson==null){
            return null;
        }
        return userJson.getString("userName");
    }
    public static String ifLoginGetName(HttpServletRequest request) throws ToolsException {
        JSONObject userJson = getUserJson(request);
        if(userJson==null){
            ValueUtil.isError("登录超时，或用户未登录");
        }
        return userJson.getString("userName");
    }

    public static JSONObject getUserInfo(HttpServletRequest request) throws ToolsException {

        return getUserJson(request);
    }

    private static JSONObject getUserJson(HttpServletRequest request) throws ToolsException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String auth = httpRequest.getHeader("Authorization");
//        String requestPerm = httpRequest.getHeader("RequestPerm");

        if ((auth != null) && (auth.length() > 7))
        {
            String HeadStr = auth.substring(0, 6).toLowerCase();
            if (HeadStr.compareTo("bearer") == 0)
            {

                auth = auth.substring(7, auth.length());
                Claims claims = JwtHelper.parseJWT(auth, Audience.base64Secret);
                    if ( claims!= null)
                {
                    String username = (String) claims.get("unique_name");
                    String id = (String) claims.get("userId");
                    JSONObject userJson = new JSONObject();
                    userJson.put("userName",username);
                    userJson.put("id",id);
                    return userJson;
                }
            }
        }

//        ValueUtil.isError("用户未登录或已登录超时,请重新登录");

        return null;
    }

//    public static JSONObject getUserInfo(String username) {
//        String userInfo = RedisCache.get(Statement.USER_INFO+username);
//        JSONObject userJson = JSON.parseObject(userInfo);
//        return userJson;
//    }
}
