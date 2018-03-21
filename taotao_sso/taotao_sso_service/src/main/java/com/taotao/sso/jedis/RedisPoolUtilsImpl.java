package com.taotao.sso.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 单机版 实现
 * @author Jeff Huang
 * @version 1.0
 * @description com.taotao.manager.redis
 * @date 2018/3/1
 */
public class RedisPoolUtilsImpl implements RedisUtils {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public void set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        //把连接还给连接池
        jedis.close();
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String json = jedis.get(key);
        jedis.close();
        return json;
    }

    @Override
    public void del(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        jedis.close();
    }

    @Override
    public void expire(String key, Integer seconds) {
        Jedis jedis = jedisPool.getResource();
        jedis.expire(key, seconds);
        jedis.close();
    }

    @Override
    public void set(String key, String value, Integer seconds) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        jedis.expire(key, seconds);
        jedis.close();
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long incr = jedis.incr(key);
        jedis.close();
        return incr;
    }
}
