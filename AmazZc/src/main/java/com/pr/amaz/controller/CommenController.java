package com.pr.amaz.controller;


import com.pr.amaz.service.AService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class CommenController {

    private Logger logger = LoggerFactory.getLogger(CommenController.class);

    @Autowired
    private AService aService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @ResponseBody
    @RequestMapping(value = "DataService",method = RequestMethod.POST)
    public HashMap<String,Object> getDataSevice(HttpServletRequest request, HttpServletResponse response){
        logger.info("从前断调用后台-------");
        redisTemplate.opsForValue().set("123456","123456",60000);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("1123","1234");
        return map;
    }
}
