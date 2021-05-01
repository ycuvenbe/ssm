package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * Created by Sfwn on 2015/9/10.
 */
@ResponseBody
@Controller
public class HelloController {
    @RequestMapping({"/hello", "lj", "/"})
    public ModelAndView hello(HttpServletRequest request , HttpServletResponse response) {
        System.out.println("hello");
        return  new ModelAndView("fileupload");
    }
}