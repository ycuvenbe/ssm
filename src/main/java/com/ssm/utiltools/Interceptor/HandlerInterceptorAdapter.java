package com.ssm.utiltools.Interceptor;

import com.ssm.utiltools.basic.ValueUtil;
import com.ssm.utiltools.jwt.UserUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${shuang} on 2017/8/10.
 */
public class HandlerInterceptorAdapter implements HandlerInterceptor {

    //不需要登录直接进入
    protected static List<String> whiteList = new ArrayList<>();
//       * 封装，必须登录的接口
    protected static List<String> mustLoginList = new ArrayList<String>();


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        whiteList.add(("/overt"));
        mustLoginList.add("/member");
        String auth = httpServletRequest.getHeader("Authorization");
        String partlyUrl = httpServletRequest.getRequestURI();//  eg:/user/login
        String method = httpServletRequest.getMethod();//eg：　POST
        System.out.println("请求路径为==》"+partlyUrl);
        System.out.println("jwtToke ==》"+auth);
//        System.out.println("请求报文为 ==》"+HttpRequestUtils.getAllParams(httpRequest));
        if(ispass(partlyUrl)){
            return true ;
        }
        if(auth!=""){
            String userName = UserUtils.getUserName(httpServletRequest);
            if(mustLogin(partlyUrl)&& ValueUtil.notEmpity(userName)){
                return true ;
            }
        }
//        ValueUtil.isError("用户未登录或已登录超时,请重新登录");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public boolean ispass(String url){
        for (String whitePath: whiteList  ) {
            int index = url.indexOf(whitePath);
            if(index>=0){
                return true;
            }
        }
        return false;
    }

    private Boolean mustLogin(String partlyUrl) {
        for (String beLogin : mustLoginList) {
            int index = partlyUrl.indexOf(beLogin);
            if(index>=0){
                return true;
            }
        }
        return false;
    }
}
