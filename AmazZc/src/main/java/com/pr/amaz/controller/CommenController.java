package com.pr.amaz.controller;


import com.pr.amaz.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommenController {

    @Autowired
    private AService aService;

    @ResponseBody
    @RequestMapping(value = "hello",method = RequestMethod.GET) 
    public String test(){
        System.out.println("进入到测试方法");
        return "Hello World";
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public String getUser(){
        aService.insertA();
        return "查找";
    }
}
