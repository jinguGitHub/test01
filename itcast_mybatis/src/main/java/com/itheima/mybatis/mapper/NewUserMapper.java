package com.itheima.mybatis.mapper;

import com.github.abel533.mapper.Mapper;
import com.itheima.mybatis.pojo.User;
import com.sun.javafx.collections.MappingChange;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.itheima.mybatis.mapper
 * @date 2018/2/5
 */
public interface NewUserMapper extends Mapper<User>{
    /****
     * 分页查询
     * @param map
     * @return
     */
    List<User> queryUserByPage(Map<String,Integer> map);

}
