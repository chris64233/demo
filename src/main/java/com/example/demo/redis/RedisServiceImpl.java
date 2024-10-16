package com.example.demo.redis;

import com.example.demo.domain.ActivityBO;
import com.example.demo.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p></p> 撮合redis相关工具类
 *
 * @author znz
 * @version 2018/12/12
 */
@Slf4j
@Component
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name = "redisCacheTemplate")
    private RedisTemplate redisCacheTemplate;


    @Override
    public Boolean saveIfAbsentWithTimeOut(String key, String value, long time, TimeUnit timeUnit) {

        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    @Override
    public Boolean save(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, JsonUtil.toJson(value).get());
    }

    @Override
    public void hmSet(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hmSet(String key, Map<String, ActivityBO> map, String ext) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hmSetExpire(String key, Map<String, Integer> map, long time, TimeUnit timeUnit) {
        redisCacheTemplate.opsForHash().putAll(key, map);
        redisCacheTemplate.opsForHash().getOperations().expire(key, time, timeUnit);

    }

    @Override
    public void hSet(String key, String hashKey, Integer value, long time, TimeUnit timeUnit) {
        redisCacheTemplate.opsForHash().put(key, hashKey, value);
        redisCacheTemplate.opsForHash().getOperations().expire(key, time, timeUnit);
    }

    @Override
    public <T> T hGet(String redisKey, String mapKey, Class<T> tClass) {
        String value;
        try {
            Object o = redisTemplate.opsForHash().get(redisKey, mapKey);
            if (o != null) {
                value = JsonUtil.toJson(o).get();
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("hget redis error; key : {}", redisKey, e);
            return null;
        }

        if (StringUtils.isBlank(value)) {
            return null;
        }
        return JsonUtil.fromJson(value, tClass).get();
    }

    @Override
    public <T> List<T> hmGet(String redisKey, List<String> mapKey, Class<T> tClass) {
        String value;
        try {
            Object o = redisTemplate.opsForHash().multiGet(redisKey, mapKey);
            value = JsonUtil.toJson(o).get();
        } catch (Exception e) {
            log.error("hmGet redis error; key : {}", redisKey, e);
            return new ArrayList<>();
        }


        if (StringUtils.isBlank(value)) {
            return new ArrayList<>();
        }
        List<T> list = JsonUtil.fromJsonArr(value, tClass);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public <T> Map<String, T> hmAll(String redisKey, Class<T> tClass) {
        String value;
        try {
            Object o = redisCacheTemplate.opsForHash().entries(redisKey);
            value = JsonUtil.toJson(o).get();
        } catch (Exception e) {
            log.error("hmAll redis error; key : {}", redisKey, e);
            return new HashMap<>();
        }

        if (StringUtils.isBlank(value)) {
            return new HashMap<>();
        }

        return JsonUtil.fromJsonMap(value, tClass);
    }

    @Override
    public <T> Map<String, T> hmAll(String redisKey, Class<T> tClass, String ext) {
        String value;
        try {
            Object o = redisTemplate.opsForHash().entries(redisKey);
            value = JsonUtil.toJson(o).get();
        } catch (Exception e) {
            log.error("hmAll redis error; key : {}", redisKey, e);
            return new HashMap<>();
        }

        if (StringUtils.isBlank(value)) {
            return new HashMap<>();
        }

        return JsonUtil.fromJsonMap(value, tClass);
    }


    @Override
    public Long hIncrement(String key, String hashKey, int dailyCount) {
        return redisCacheTemplate.opsForHash().increment(key, hashKey, dailyCount);
    }

    /**
     * 通过key删除 键值对
     *
     * @param key 要删除的key
     * @return 是否删除成功
     */
    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public String get(String key) {
        Object o;
        try {
            o = redisTemplate.opsForValue().get(key);
            if (o != null) {
                return o.toString();
            }
        } catch (Exception e) {
            log.error("get redis error; key : {}", key, e);
        }

        return null;
    }

    @Override
    public <T> T get(String key, Class<T> tClass) {
        String str = null;
        try {
            str = (String) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("get redis error; key : {}", key, e);
        }
        if (StringUtils.isBlank(str)) {
            return null;
        }

        return JsonUtil.fromJson(str, tClass).get();
    }

}
