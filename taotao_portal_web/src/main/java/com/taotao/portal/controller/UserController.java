package com.taotao.portal.controller;

import com.taotao.manager.pojo.User;
import com.taotao.portal.ultis.CookieUtils;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.portal.controller
 * @date 2018/3/20
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    // url : "/user/doRegister.html",
    @RequestMapping(value ="doRegister",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doRegister(User user){
        Map<String,Object> map = new HashMap<>();
        try {
            userService.doRegister(user);
            map.put("status",200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data",e.getMessage());
        }
        return map;
    }

    @Value("${TT_TICKET}")
    private String TT_TICKET;

    /**
     * 实现用户登录
     * @param user
     * @param request
     * @param response
     * @return
     */
    //url: "/service/user/doLogin?r=" + Math.random()
    @RequestMapping(value = "doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> doLogin(User user, HttpServletRequest request, HttpServletResponse response){
        // 调用服务进行用户登录，需要返回ticket，目的是放到cookie中给用户
        System.out.println("hahahahahhahahhhaha");
        String ticket = userService.doLogin(user);
        //判断ticket是否为空,如果不为空则表示登录成功
        if (StringUtils.isNotBlank(ticket)) {
            //登录成功  需要将登录信息保存到cookie中
            CookieUtils.setCookie(request,response,TT_TICKET,ticket,60*60*24,true);

            //封装返回数据
            Map<String,Object> map = new HashMap<>();
            map.put("status",200);
            return map;
        }
        return null;
    }
}
