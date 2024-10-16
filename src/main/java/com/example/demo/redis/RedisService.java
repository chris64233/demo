package com.example.demo.redis;

import com.example.demo.domain.ActivityBO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface RedisService {


    /**
     * 保存key-value 若有重复无法保存
     *
     * @param key   key
     * @param value value
     * @return 是否保存成功
     */
    Boolean saveIfAbsentWithTimeOut(String key, String value,long time,TimeUnit timeUnit) ;
    Boolean save(String key, String value);
    void save(String key, Object value);

    void hmSet(String key, Map<String, Object> map);
    void hmSet(String key, Map<String, ActivityBO> map, String ext);
    void hmSetExpire(String key, Map<String, Integer> map, long time, TimeUnit timeUnit);

    void hSet(String key, String hashKey, Integer value, long time, TimeUnit timeUnit);


    <T> T hGet(String redisKey, String mapKey, Class<T> tClass);
    <T> List<T> hmGet(String redisKey, List<String> mapKey, Class<T> tClass);
    <T> Map<String, T> hmAll(String redisKey, Class<T> tClass);
    <T> Map<String, T> hmAll(String redisKey, Class<T> tClass, String ext);



    /**
     * 通过key删除 键值对
     *
     * @param key 要删除的key
     * @return 是否删除成功
     */
    Boolean delete(String key);


    String get(String key);
    <T> T get(String key, Class<T> tClass);

    Long hIncrement(String key, String hashKey, int dailyCount);
}
