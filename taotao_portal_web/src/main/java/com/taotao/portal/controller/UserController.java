package com.taotao.portal.controller;

import com.taotao.manager.pojo.User;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
