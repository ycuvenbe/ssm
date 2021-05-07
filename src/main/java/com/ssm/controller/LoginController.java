package com.ssm.controller;

import com.ssm.Multipledata.DataSourceContextHolder;
import com.ssm.mapper.UserMapper;
import com.ssm.po.User;
import com.ssm.po.UserCustom;
import com.ssm.po.UserExample;
import com.ssm.utiltools.basic.ValueUtil;
import com.ssm.utiltools.error.ToolsException;
import com.ssm.utiltools.jwt.AccessToken;
import com.ssm.utiltools.jwt.Audience;
import com.ssm.utiltools.jwt.JwtHelper;
import com.ssm.utiltools.jwt.UserUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Mars-Hasee on 2017/8/9.
 */
@RestController
@RequestMapping("/sso")
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/login/overt" ,method =RequestMethod.POST )
    public String login(@RequestParam Map<String,String> params)  {
        DataSourceContextHolder.setDbType("files");
        try {
            ValueUtil.verify(params.get("userName"));
            ValueUtil.verify(params.get("passWord"));
        } catch (ToolsException e) {
            return ValueUtil.toError(e.getCode(),e.getMessage());
        }

        String userName = params.get("userName");
        String passWord = params.get("passWord");
        BASE64Encoder en=new BASE64Encoder();
        String pass =userName+passWord;
        String word =null;
        try {
            word= en.encode(pass.getBytes("utf-8"));
            System.out.println("加密密码:"+word);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        UserCustom userCustom = new UserCustom();
        userCustom.setUserName(userName);
        userCustom.setPassWord(word);
        User user = userMapper.findByUserNameAndPassword(userCustom);
        if(  ValueUtil.isEmpity(user)) {
            return ValueUtil.toError("500","用户名密码错误");
        }
        //拼装accessToken
        String accessToken = JwtHelper.createJWT(userName, user.getId().toString(),
                "一般用户", Audience.clientId, Audience.name,
                Audience.expiresSecond* 1000 , Audience.base64Secret);
        //返回accessToken
        AccessToken accessTokenEntity = new AccessToken();
        accessTokenEntity.setAccess_token(accessToken);
        accessTokenEntity.setExpires_in(Audience.expiresSecond);
        accessTokenEntity.setToken_type("bearer");
        accessTokenEntity.setUserId(user.getId());
        return ValueUtil.toJson(HttpStatus.SC_CREATED,accessTokenEntity);
    }

    //注册
    @RequestMapping(value = "/register/overt",method = RequestMethod.POST)
    public String register( String userName,String passWord ,String VerifCode,String email, HttpServletRequest request ) {
        DataSourceContextHolder.setDbType("files");
        Object code = request.getSession().getAttribute("codeVerification");
        if(ValueUtil.isEmpity(code)){
            return  ValueUtil.toError(HttpStatus.SC_INTERNAL_SERVER_ERROR,"验证码失效请重新获取");
        }
        if(!code.equals(VerifCode)){
             return    ValueUtil.toError(HttpStatus.SC_INTERNAL_SERVER_ERROR,"验证码错误");
        }
        BASE64Encoder en=new BASE64Encoder();
        String pass =userName+passWord; String word=null;
        try {
            word=  en.encode(pass.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        UserCustom userCustom = new UserCustom();
        userCustom.setUserName(userName);
        userCustom.setPassWord(word);
        userCustom.setEmail(email);
        userMapper.insertOne(userCustom);
        return ValueUtil.toJson(HttpStatus.SC_CREATED,"恭喜你注册成功");
    }

    //验证用户名
    @RequestMapping(value = "/checkName/overt",method = RequestMethod.GET)
    public String checkName( String userName ) {
        DataSourceContextHolder.setDbType("files");
       User user =  userMapper.findByUserName(userName);
       if (ValueUtil.notEmpity(user)){
           return ValueUtil.toJson(HttpStatus.SC_CONFLICT,"用户名被占用");
       }else {
           return ValueUtil.toJson(HttpStatus.SC_OK,"用户名可用");
       }
    }
    //验证邮箱
    @RequestMapping(value = "/checkEmail/overt",method = RequestMethod.GET)
    public String checkEmail( String email ) {
        DataSourceContextHolder.setDbType("files");
        User user =  userMapper.findByemail(email);
        if (ValueUtil.notEmpity(user)){
            return ValueUtil.toJson(HttpStatus.SC_CONFLICT,"邮箱已注册");
        }else {
            return ValueUtil.toJson(HttpStatus.SC_OK,"邮箱可用");
        }
    }


    //验证token有效性
    @RequestMapping(value = "/checkToken/overt",method = RequestMethod.GET)
    public String checkToken( HttpServletRequest request ) {
        try {
            String username = UserUtils.getUserName(request);
            if(username==null){
                ValueUtil.isError("token已失效");
            }
            return ValueUtil.toJson("token有效");
        } catch (ToolsException e) {
            return ValueUtil.toError(e.getCode(),e.getMessage());
        }
    }


    //从token中获取用户名
    @RequestMapping(value = "/member",method = RequestMethod.GET)
    public String member( HttpServletRequest request ) {
        String username=null;
        try {
            username = UserUtils.getUserName(request);
        } catch (ToolsException e) {
            e.printStackTrace();
        }
        return ValueUtil.toJson(username+"欢迎你");
    }

}
