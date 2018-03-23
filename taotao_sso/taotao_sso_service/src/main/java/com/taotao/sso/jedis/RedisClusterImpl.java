package com.taotao.sso.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.redis
 * @date 2018/3/1
 */
public class RedisClusterImpl implements RedisUtils {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        //这里不能关闭jedisCluster
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        String json = jedisCluster.get(key);
        return json;
    }

    @Override
    public void del(String key) {
        jedisCluster.del(key);
    }

    @Override
    public void expire(String key, Integer seconds) {
        jedisCluster.expire(key, seconds);
    }

    @Override
    public void set(String key, String value, Integer seconds) {
        jedisCluster.set(key, value);
        jedisCluster.expire(key, seconds);
    }

    @Override
    public Long incr(String key) {
        Long incr = jedisCluster.incr(key);
        return incr;
    }
}
