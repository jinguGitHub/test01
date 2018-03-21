package com.taotao.sso.controller;

import com.taotao.manager.pojo.User;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现根据ticket查询用户接口
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.sso.controller
 * @date 2018/3/16
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 验证数据是否可用
     * @param param
     * @param type
     * @param callback
     * @return
     */
    //验证数据是否可用http://sso.taotao.com/user/check/zhangsan/1
    @RequestMapping(value = "check/{param}/{type}",method = RequestMethod.GET)
    public ResponseEntity<String> check(@PathVariable("param")String param,
                                          @PathVariable("type")Integer type,
                                          String callback){
        //如果type类型不在1,2,3则返回参数无效代码
        if (type<1 || type >3){
            //返回400无效编码
            //修改返回值支持jsonp，接收callback伪装成js数据 根据url可知 数据格式是callback+jsonp字符串
            // 于是下面拼接成:callback+"("+bool+")"形式 返回给客户端需要的形式
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(callback+"("+false+")");
        }
        try {
            Boolean bool = userService.check(param,type);
            //返回200
            return ResponseEntity.status(HttpStatus.OK).body(callback+"("+bool+")");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(callback+"("+false+")");
    }

    /**
     * 实现根据ticket查询用户接口
     * @param ticket
     * @return
     */
    //GET请求
    //http://sso.taotao.com/user/{ticket}
    @RequestMapping(value = "{ticket}")
    public ResponseEntity<User> queryUserByTicket(@PathVariable("ticket")String ticket){
        //查询用户信息
        try {
            User user = userService.queryUserByTicket(ticket);
            if (user!=null){
                //查询到用户信息
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }else {
                //如果找不到数据 就说明没有登录 返回404错误提示
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //发生异常 返回 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
