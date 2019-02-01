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
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    public String getDataSevice(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if (session==null){
            try {
                response.sendRedirect(request.getContextPath() + "/index");
            } catch (IOException e) {
                logger.info("重新定向抛出异常" + e.toString());
            }
        }else{
            session.getId();

        }
        HashMap<String,Object> map = new HashMap<>();

        return session.getId();
    }

    /**
     * 登录
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "LoginService",method = RequestMethod.POST)
    public HashMap<String,Object> userLogin(HttpServletRequest request,HttpServletResponse response){
        logger.info("用户登录");
        HttpSession session =  request.getSession(true);
        logger.info(session.getId());
        session.setAttribute("userid",123456);
        HashMap<String,Object> resultMap = new HashMap<>();
        return resultMap;
    }
}
