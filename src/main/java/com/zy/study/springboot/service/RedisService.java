package com.zy.study.springboot.service;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by zy on 17-8-30.
 */
@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveValue(String key, Object value) {
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(value));
    }

    public <T> T readObject(String key, Class<T> tClass) {
        Gson gson = new Gson();
        String stringVal = redisTemplate.opsForValue().get(key);
        T t = null;
        if(StringUtils.isNotEmpty(stringVal)) {
            t = gson.fromJson(stringVal, tClass);
        }
        return t;
    }
}
