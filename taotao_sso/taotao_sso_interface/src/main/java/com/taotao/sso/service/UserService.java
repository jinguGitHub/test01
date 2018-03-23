package com.taotao.sso.service;

import com.taotao.manager.pojo.User;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.service
 * @date 2018/3/16
 */
public interface UserService {
    /**
     * 查询数据是否可用    (像京东注册时用户名验证是否可用)
     * @param param
     * @param type
     * @return
     */
    Boolean check(String param, Integer type);

    /**
     * 实现根据ticket查询用户接口
     * @param ticket
     * @return
     */
    User queryUserByTicket(String ticket);

    /**
     * 用户注册
     * @param user
     */
    void doRegister(User user);

    /**
     * 实现用户登录
     * @param user
     * @return
     */
    String doLogin(User user);


}
