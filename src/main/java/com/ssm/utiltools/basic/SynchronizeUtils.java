package com.ssm.utiltools.basic;//package com.union.utiltools.basic;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Enumeration;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * Created by SJQ on 2017/4/21.
// */
//public class SynchronizeUtils {
//
//    public static String getResult(String host, String url, RequestMethod method, String jsonData) {
//        int i = 0;
//        String result = null;
//        while (i < 2) {
//            try {
//                i++;
//                HttpBean httpBean = new HttpBean(host + url, method);
//                if(jsonData!=null&&!jsonData.equals("")){
//                    httpBean.addParameter("jsonData", jsonData);
//                    httpBean.run();
//                }
//                result = httpBean.getResponseContent();
//                if(result!=null){
//                    String code = JSON.parseObject(result).getString("code");
//                    if(code!=null&&(code.equals("201")||code.equals("204")||code.equals("200")||code.equals("500"))){
//                        break;
//                    }else{
//                        result = null;
//                    }
//                }
//            } catch (Exception e) {
//                continue;
//            }
//
//        }
//        return result;
//    }
//
//    public static String getResult(String host, String url, RequestMethod method, Map<String,Object> paramsData, HttpServletRequest request) {
//        int i = 0;
//        String result = null;
//        while (i < 2) {
//            try {
//                i++;
//                HttpBean httpBean = new HttpBean(host + url, method);
//                if(paramsData!=null&&paramsData.size()>0){
//                    Iterator it = paramsData.entrySet().iterator();
//                    while (it.hasNext()){
//                        Map.Entry<String,Object> p = (Map.Entry) it.next();
//                        String key = p.getKey();
//                        Object value = p.getValue();
//                        httpBean.addParameter(key, value);
//                    }
//                }
//                if (request!=null){
//                    Enumeration headerNames = request.getHeaderNames();
//                    while (headerNames.hasMoreElements()) {
//                        String key = (String) headerNames.nextElement();
//                        if(key.equals("authorization")||key.equals("requestperm")){
//                            String value = request.getHeader(key);
//                            httpBean.addHeader(key,value);
//                        }
//                    }
//                }
//                httpBean.run();
//                result = httpBean.getResponseContent();
//                if(result!=null){
//                    String code = JSON.parseObject(result).getString("code");
//                    if(code!=null){
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                continue;
//            }
//
//        }
//        return result;
//    }
//
//    public static String getCode(String host, String url, RequestMethod method, Map<String,Object> paramsData, HttpServletRequest request) {
//        int i = 0;
//        String code = null;
//        while (i < 2) {
//            try {
//                i++;
//                HttpBean httpBean = new HttpBean(host + url, method);
//                if(paramsData!=null&&paramsData.size()>0){
//                    Iterator it = paramsData.entrySet().iterator();
//                    while (it.hasNext()){
//                        Map.Entry<String,Object> p = (Map.Entry) it.next();
//                        String key = p.getKey();
//                        Object value = p.getValue();
//                        httpBean.addParameter(key, value);
//                    }
//                }
//                if (request!=null){
//                    Enumeration headerNames = request.getHeaderNames();
//                    while (headerNames.hasMoreElements()) {
//                        String key = (String) headerNames.nextElement();
//                        if(key.equals("Authorization")||key.equals("RequestPerm")){
//                            String value = request.getHeader(key);
//                            httpBean.addHeader(key,value);
//                        }
//                    }
//                }
//                httpBean.run();
//                String result = httpBean.getResponseContent();
//                if(result!=null){
//                    JSONObject obj = JSON.parseObject(result);
//                    code = obj.getString("code");
//                    break;
//                }
//            } catch (Exception e) {
//                continue;
//            }
//
//        }
//        return code;
//    }
//
//    public static String paramsCode(String host, String url, RequestMethod method, Map<String,Object> params) {
//        int i = 0;
//        String code = null;
//        while (i < 2) {
//            try {
//                i++;
//                HttpBean httpBean = new HttpBean(host + url, method);
//                if(params!=null&&!params.equals("")){
//                    httpBean.addParameter("userName", params.get("userName"));
//                    httpBean.addParameter("bean", params.get("bean"));
//                    httpBean.addParameter("status", params.get("status"));
//                    httpBean.addParameter("channelCode", params.get("channelCode"));
//                    httpBean.addParameter("orderNumber", params.get("orderNumber"));
//                    httpBean.addParameter("returnBean", params.get("returnBean"));
//                    httpBean.addParameter("newBeans", params.get("newBeans"));
//                    httpBean.addParameter("point", params.get("point"));
//                    httpBean.addParameter("userId", params.get("userId"));
//                    httpBean.run();
//                }
//               String result = httpBean.getResponseContent();
//                if(result!=null){
//                     code = JSON.parseObject(result).getString("code");
//                    if(code!=null&&(code.equals("201")||code.equals("204")||code.equals("200")||code.equals("500"))){
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                continue;
//            }
//
//        }
//        return code;
//    }
//
//    public static String getResult(String host, String url, RequestMethod method, String paramName, String jsonData) {
//        int i = 0;
//        String result = null;
//        while (i < 2) {
//            try {
//                i++;
//                HttpBean httpBean = new HttpBean(host + url, method);
//                if(paramName!=null&&!paramName.equals("")){
//                    httpBean.addParameter(paramName, jsonData);
//                    httpBean.run();
//                }
//                result = httpBean.getResponseContent();
//                if(result!=null){
//                    String code = JSON.parseObject(result).getString("code");
//                    if(code!=null&&(code.equals("201")||code.equals("204")||code.equals("200")||code.equals("500"))){
//                        break;
//                    }else{
//                        result = null;
//                    }
//                }
//            } catch (Exception e) {
//                continue;
//            }
//
//        }
//        return result;
//    }
//
//    public static String getOmsResult(String host, String url, RequestMethod method, String paramName, String jsonData)  {
//        DataOutputStream wr = null;
//        DataInputStream rd = null;
//        HttpURLConnection conn = null;
//        System.out.println("请求oms==》 url:"+host+url+"   参数为："+jsonData);
//        try{
//            URL requestUrl = new URL(host+url);
//            conn = (HttpURLConnection) requestUrl.openConnection();
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Charset", "utf-8");
//            conn.setRequestMethod("POST");
//            conn.connect();
//            wr = new DataOutputStream(conn.getOutputStream());
//            wr.write(jsonData.getBytes("utf-8"));
//            wr.flush();
//            DataInputStream dis = new DataInputStream(conn.getInputStream());
//            byte[] aryZlib = streamToByteArray(dis);
//            if (dis != null) {
//                dis.close();
//                dis = null;
//            }
//            return new String(aryZlib, "utf-8");
//        } catch(Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rd != null) {
//                try {
//                    rd.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                rd = null;
//            }
//            if (wr != null) {
//                try {
//                    wr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                wr = null;
//            }
//            if (conn != null) {
//                conn.disconnect();
//                conn = null;
//            }
//        }
//        return null;
//    }
//
//    public static byte[] streamToByteArray(InputStream in) throws IOException {
//        byte[] buf = new byte[100];
//        byte[] dest = new byte[0];
//        int len = -1;
//        while ((len = in.read(buf)) != -1) {
//            byte[] tmp = new byte[dest.length + len];
//            System.arraycopy(dest, 0, tmp, 0, dest.length);
//            System.arraycopy(buf, 0, tmp, dest.length, len);
//            dest = tmp;
//        }
//        return dest;
//    }
//
//    public static String getCode(String host, String url, String jsonData, RequestMethod method) {
//        int i = 0;
//        String code = null;
//        while (i < 2) {
//            try {
//                i++;
//                HttpBean httpBean = new HttpBean(host + url, method);
//                httpBean.addParameter("jsonData", jsonData);
//                httpBean.run();
//                String result = httpBean.getResponseContent();
//                if(result!=null){
//                    code = ValueUtil.getFromJson(result, "code");
//                    if(code!=null&&(code.equals("201")||code.equals("204")||code.equals("200")||code.equals("500"))){
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                continue;
//            }
//
//        }
//        return code;
//    }
//}
