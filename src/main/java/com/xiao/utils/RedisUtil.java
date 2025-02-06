package com.xiao.utils;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisUtil {
    @Resource
    RedisTemplate<Object, Object> redisTemplate;
    public void set(Object key, Object val, long exp, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, val, exp, unit);
    }
    public void expire(Object key, long exp, TimeUnit unit) {
        redisTemplate.expire(key, exp ,unit);
    }
    public void del(Object key) {
        redisTemplate.delete(key);
    }
    public Object get(Object key) {
        return redisTemplate.opsForValue().get(key);
    }
    public Set<Object> getKeys() {
        return redisTemplate.keys("*");
    }
    public long getExp(Object key) {
        return redisTemplate.getExpire(key);
    }
}
