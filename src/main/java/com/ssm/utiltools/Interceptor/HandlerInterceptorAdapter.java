package com.ssm.utiltools.Interceptor;

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
        if(ispass(partlyUrl)){
            return true ;
        }
        if(auth!=""){
            String userName = UserUtils.getUserName(httpServletRequest);
            if(mustLogin(partlyUrl)){
                return true ;
            }
        }


//        if ((auth != null) && (auth.length() > 7))
//        {
//            String HeadStr = auth.substring(0, 6).toLowerCase();
//            if (HeadStr.compareTo("bearer") == 0)
//            {
//
//                auth = auth.substring(7, auth.length());
//                Claims claims = JwtHelper.parseJWT(auth, Audience.base64Secret);
//                if (claims != null)
//                {
//                    claims.get("userName")
//                    return;
//                }
//            }
//        }


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
