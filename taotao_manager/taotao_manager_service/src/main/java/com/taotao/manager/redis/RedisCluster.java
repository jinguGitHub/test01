package com.taotao.manager.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.redis
 * @date 2018/3/1
 */
public class RedisCluster implements RedisUtils {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        this.jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        String result = this.jedisCluster.get(key);
        return result;
    }

    @Override
    public void del(String key) {
        this.jedisCluster.del(key);
    }

    @Override
    public void expire(String TAOTAO_PORTAL_AD_ID, String key, Integer seconds) {
        this.jedisCluster.expire(key, seconds);
    }

    @Override
    public void set(String key, String value, Integer seconds) {
        this.jedisCluster.set(key, value);
        this.jedisCluster.expire(key, seconds);
    }

    @Override
    public Long incr(String key) {
        Long count = this.jedisCluster.incr(key);
        return count;
    }
}
