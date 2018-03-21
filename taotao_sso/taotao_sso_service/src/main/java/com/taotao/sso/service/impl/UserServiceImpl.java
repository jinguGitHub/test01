package com.taotao.sso.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.mapper.UserMapper;
import com.taotao.manager.pojo.User;
import com.taotao.sso.jedis.RedisUtils;
import com.taotao.sso.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.sso.service
 * @date 2018/3/16
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean check(String param, Integer type) {
        User user = new User();
        switch (type) {
            //返回根据对应的type设置不同的查询条件
            case 1:user.setUsername(param); break;
            case 2:user.setPhone(param);break;
            case 3:user.setEmail(param);break;
            default:break;
        }
            int count = userMapper.selectCount(user);
            // 根据条件查询，如果查到值，则表示数据已经使用了，返回false，反之返回true
            if (count>0){
                return false;
            }else {
                return true;
            }
        }

        @Autowired
        private RedisUtils redisUtils;
        @Value("${TAOTAO_SSO_USER_SESSION_KEY}")
        private String TAOTAO_SSO_USER_SESSION_KEY;

        //用法有三个: 1.把json串转成 对象 2.把对象转成json串 3.解析json串
        private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 根据ticket查询用户的 Service接口实现类
     * @param ticket
     * @return
     */
    @Override
    public User queryUserByTicket(String ticket) {
        User user = null;
        //从redis中获取用户登录信息
        String jsonStr = redisUtils.get(TAOTAO_SSO_USER_SESSION_KEY + ticket);
        //如果为空则用户 没有登录
        if (StringUtils.isNotBlank(jsonStr)){
            //如果用户要验证登录,说明用户是活动状态,需要重新设置用户登录有效时间
            redisUtils.expire(TAOTAO_SSO_USER_SESSION_KEY + ticket, 60 * 60);
        }
        try {
            //把json串转换成用户对象
            objectMapper.readValue(jsonStr,User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void doRegister(User user) {
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        //加密密码,使用md5加密
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));

        //保存用户
        userMapper.insertSelective(user);
    }
}
