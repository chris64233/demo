package com.example.demo.onceTask.jupiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 缓存工具类
 *
 * @author xuzhuqing
 * @version 1.0
 * @date 2022-08-31 17:20:28
 */
@Slf4j
@Component
public class JupiterCacheUtil {

    /**
     * 默认缓存过期时间（秒）- 24小时
     */
    private static long DEFAULT_CACHE_TIMEOUT = 24 * 60 * 60;

    /**
     * 随机过期时间最大值（分钟）
     */
    private static int MAX_RANDOM_MINUTES = 30;


    @Value("${spring.cache.default-timeout:86400}")
    public void setDefaultCacheTimeout(long timeout) {
        DEFAULT_CACHE_TIMEOUT = timeout > 0 ? timeout : 86400;
    }

    @Value("${spring.cache.random-minutes:30}")
    public void setMaxRandomMinutes(int minutes) {
        MAX_RANDOM_MINUTES = minutes > 0 ? minutes : 30;
    }


    /**
     * 生成带随机偏移的过期时间
     *
     * @return 过期时间（秒）
     */
    private static long generateExpirationTime() {
        int randomMinutes = new Random().nextInt(MAX_RANDOM_MINUTES);
        return DEFAULT_CACHE_TIMEOUT + (randomMinutes * 60L);
    }

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        JupiterCacheUtil.redisTemplate = redisTemplate;
    }

    private static String uniquePrefix;

    /**
     * 缓存锁的默认超时时间（秒）
     */
    private static long DEFAULT_LOCK_TIMEOUT = 30;

    @Value("${spring.cache.unique-prefix:}")
    public void setUniquePrefix(String prefix) {
        uniquePrefix = null;
        if (CommonUtil.isNotEmpty(prefix)) {
            uniquePrefix = String.format("%s:", prefix);
        }
    }

    @Value("${spring.cache.redis.lock-timeout:30}")
    public void setDefaultLockTimeout(long timeout) {
        DEFAULT_LOCK_TIMEOUT = timeout > 0 ? timeout : 30;
    }

    /**
     * Keys
     *
     * @param key the key
     * @return set the set
     * @author xuzhuqing
     * @date 2022-08-31 18:31:14
     */
    public static Set<String> keys(String key) {
        return redisTemplate.keys(getUniqueKey(key) + "*");
    }

    /**
     * 判断key是否需要添加前缀
     *
     * @param key
     * @return
     */
    public static String getUniqueKey(String key) {
        if (CommonUtil.isEmpty(uniquePrefix)) {
            return key;
        }
        if (key.contains(uniquePrefix)) {
            return key;
        }
        return uniquePrefix + key;
    }

    public static Set<String> scan(String pattern, int count) {
        Set<String> keys = new HashSet<>();
        RedisSerializer serializer = redisTemplate.getKeySerializer();
        ScanOptions scanOptions = ScanOptions.scanOptions().match(getUniqueKey(pattern)).count(count).build();
        Cursor<byte[]> cursor = redisTemplate.execute(connection -> connection.scan(scanOptions), true);
        while (cursor.hasNext()) {
            keys.add(String.valueOf(serializer.deserialize(cursor.next())));
        }
        return keys;
    }

    /**
     * 添加缓存
     *
     * @param key   the key
     * @param value the value
     * @return boolean the boolean
     * @author xuzhuqing
     * @date 2022-08-31 17:20:37
     */
    public static boolean set(String key, Object value) {
        try {
            long expireTime = generateExpirationTime();
            redisTemplate.opsForValue().set(getUniqueKey(key), value, expireTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("添加缓存异常", e);
            return false;
        }
    }

    /**
     * 设置超时时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public static boolean set(String key, Object value, long expireTime) {
        try {
            redisTemplate.opsForValue().set(getUniqueKey(key), value, expireTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("添加缓存异常", e);
            return false;
        }
    }


    /**
     * 获取缓存
     *
     * @param key the key
     * @return object the object
     * @author xuzhuqing
     * @date 2022-08-31 17:20:21
     */
    public static Object get(String key) {
        return null == key ? null : redisTemplate.opsForValue().get(getUniqueKey(key));
    }


    /**
     * 设置缓存过期时间
     *
     * @param key        the key
     * @param expireTime the expire time
     * @return the expire
     * @author xuzhuqing
     * @date 2022-08-31 17:20:13
     */
    public static boolean setExpire(String key, long expireTime) {
        try {
            if (0 < expireTime) {
                redisTemplate.expire(getUniqueKey(key), expireTime, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("设置缓存过期时间异常", e);
            return false;
        }
    }


    /**
     * 判断缓存是否存在
     *
     * @param key the key
     * @return boolean the boolean
     * @author xuzhuqing
     * @date 2022-08-31 17:20:06
     */
    public static boolean isExist(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(getUniqueKey(key)));
        } catch (Exception e) {
            log.error("判断缓存是否存在异常", e);
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key the key
     * @author xuzhuqing
     * @date 2022-08-31 17:19:53
     */
    @SuppressWarnings("unchecked")
    public static void delete(String... key) {
        if (null != key && 0 < key.length) {
            if (1 == key.length) {
                Boolean deleteStatus = redisTemplate.delete(getUniqueKey(key[0]));
                log.info("缓存" + key[0] + "删除结果" + deleteStatus);
            } else {
                List<String> keyList = Arrays.asList(key).stream().map(str -> getUniqueKey(str)).collect(Collectors.toList());
                Long deleteCount = redisTemplate.delete(keyList);
                log.info("缓存" + key + "删除数量" + deleteCount);
            }
        }
    }


    /**
     * 删除hash表中的某一个key：hdel b b1
     *
     * @param key     the key
     * @param hashKey the hash key
     * @return long the long
     * @author xuzhuqing
     * @date 2022-08-31 17:19:39
     */
    public static Long hdelete(String key, String... hashKey) {
        return redisTemplate.opsForHash().delete(getUniqueKey(key), hashKey);
    }


    /**
     * 添加缓存到hash表中
     *
     * @param key     the key
     * @param hashKey the hash key
     * @param value   the value
     * @return boolean the boolean
     * @author xuzhuqing
     * @date 2022-08-31 17:19:27
     */
    public static boolean hset(String key, String hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(getUniqueKey(key), hashKey, value);
            return true;
        } catch (Exception e) {
            log.error("hset添加缓存异常", e);
            return false;
        }
    }


    /**
     * 从指定的hash表中查找hashKey的值
     *
     * @param key     the key
     * @param hashKey the hash key
     * @return object the object
     * @author xuzhuqing
     * @date 2022-08-31 17:19:18
     */
    public static Object hget(String key, String hashKey) {
        return redisTemplate.opsForHash().get(getUniqueKey(key), hashKey);
    }


    /**
     * 获取hash表中的所有key
     *
     * @param hasKey the has key
     * @return object the object
     * @author xuzhuqing
     * @date 2022-08-31 17:19:03
     */
    public static Object hkeys(String hasKey) {
        try {
            return redisTemplate.opsForHash().keys(getUniqueKey(hasKey));
        } catch (Exception ex) {
            log.error("获取key异常" + hasKey, ex);
            return null;
        }
    }

    public static Set<Object> hkeysSet(String key) {
        try {
            return redisTemplate.opsForHash().keys(getUniqueKey(key));
        } catch (Exception e) {
            log.error("获取Hash所有字段异常，键: " + key, e);
            return Collections.emptySet();
        }
    }

    /**
     * 获取某个 hash key 中的缓存个数
     *
     * @param hasKey the has key
     * @return long the long
     * @author xuzhuqing
     * @date 2022-08-31 17:18:50
     */
    public static Long size(String hasKey) {
        return redisTemplate.opsForHash().size(getUniqueKey(hasKey));
    }


    /**
     * 原子递增
     *
     * @param key   键
     * @param delta 增量（大于0）
     * @return 递增后的值
     */
    public static long incr(String key, long delta) {
        try {
            if (delta < 0) {
                throw new IllegalArgumentException("递增因子必须大于0");
            }
            Long increment = redisTemplate.opsForValue().increment(getUniqueKey(key), delta);
            return increment != null ? increment : 0;
        } catch (Exception e) {
            log.error("递增操作异常，键: " + key + ", 增量: " + delta, e);
            return 0;
        }
    }

    /**
     * 原子递减
     *
     * @param key   键
     * @param delta 减量（大于0）
     * @return 递减后的值
     */
    public static long decr(String key, long delta) {
        try {
            if (delta < 0) {
                throw new IllegalArgumentException("递减因子必须大于0");
            }
            Long decrement = redisTemplate.opsForValue().decrement(getUniqueKey(key), delta);
            return decrement != null ? decrement : 0;
        } catch (Exception e) {
            log.error("递减操作异常，键: " + key + ", 减量: " + delta, e);
            return 0;
        }
    }

    /**
     * 构建Lua原子递增脚本
     *
     * @return Lua脚本
     */
    private static String buildLuaIncrKeyScript() {
        return "local key = KEYS[1]\n" +
                "local limit = ARGV[1]\n" +
                "local c = redis.call('get', key)\n" +
                "if c and tonumber(c) > tonumber(limit) then\n" +
                "    redis.call('set', key, 0)\n" +
                "    return c\n" +
                "end\n" +
                "return redis.call('incr', key)";
    }

    /**
     * 执行Lua脚本进行原子递增
     *
     * @param key              键
     * @param orderSnIncrLimit 递增限制
     * @return 递增后的值
     */
    public static Long luaIncrKey(String key, Integer orderSnIncrLimit) {
        try {
            RedisScript<Long> redisScript = new DefaultRedisScript<>(buildLuaIncrKeyScript(), Long.class);
            String uniqueKey = getUniqueKey(key);
            Long result = redisTemplate.execute(redisScript, Collections.singletonList(uniqueKey), orderSnIncrLimit);
            return result;
        } catch (Exception e) {
            log.error("执行Lua脚本递增异常，键: " + key, e);
            return 0L;
        }
    }

    /**
     * 删除缓存锁，用于解决缓存死锁问题
     *
     * @param cacheKey 原始缓存键（不含~lock后缀）
     * @return 是否成功删除
     */
    public static boolean deleteCacheLock(String cacheKey) {
        try {
            String lockKey = getUniqueKey(cacheKey) + "~lock";
            Boolean deleteStatus = redisTemplate.delete(lockKey);
            log.info("删除缓存锁，键: " + lockKey + ", 结果: " + deleteStatus);
            return Boolean.TRUE.equals(deleteStatus);
        } catch (Exception e) {
            log.error("删除缓存锁异常，键: " + cacheKey, e);
            return false;
        }
    }

    /**
     * 批量删除匹配模式的缓存锁
     *
     * @param pattern 匹配模式，例如"viewFlowName_*"
     * @return 成功删除的锁数量
     */
    public static int deleteCacheLocksByPattern(String pattern) {
        try {
            String lockPattern = getUniqueKey(pattern) + "~lock";
            Set<String> lockKeys = scan(lockPattern, 100);
            if (CommonUtil.isEmpty(lockKeys)) {
                return 0;
            }

            Long deleteCount = redisTemplate.delete(lockKeys);
            log.info("批量删除缓存锁，模式: " + lockPattern + ", 删除数量: " + deleteCount);
            return deleteCount != null ? deleteCount.intValue() : 0;
        } catch (Exception e) {
            log.error("批量删除缓存锁异常，模式: " + pattern, e);
            return 0;
        }
    }

    /**
     * 扫描并删除所有缓存锁
     *
     * @return 删除的锁键及其删除结果的映射
     */
    public static Map<String, Boolean> scanAndDeleteAllCacheLocks() {
        try {
            // 搜索所有以~lock结尾的键
            Set<String> lockKeys = scan("*~lock", 1000);

            if (CommonUtil.isEmpty(lockKeys)) {
                log.info("未发现任何缓存锁");
                return Collections.emptyMap();
            }

            Map<String, Boolean> deleteResults = new HashMap<>(lockKeys.size());

            // 逐个删除并记录结果
            for (String lockKey : lockKeys) {
                Boolean result = redisTemplate.delete(lockKey);
                deleteResults.put(lockKey, Boolean.TRUE.equals(result));
                log.info("删除缓存锁: " + lockKey + ", 结果: " + result);
            }

            log.info("缓存锁清理完成，共处理: " + lockKeys.size() + " 个锁");
            return deleteResults;
        } catch (Exception e) {
            log.error("扫描并删除缓存锁异常", e);
            return Collections.emptyMap();
        }
    }

    /**
     * 查找可能的死锁键（存在时间超过阈值的锁）
     *
     * @param timeThresholdSeconds 时间阈值（秒），超过此时间的锁可能是死锁
     * @return 可能的死锁键列表
     */
    public static List<String> findPotentialDeadlocks(long timeThresholdSeconds) {
        try {
            // 搜索所有以~lock结尾的键
            Set<String> lockKeys = scan("*~lock", 1000);

            if (CommonUtil.isEmpty(lockKeys)) {
                return Collections.emptyList();
            }

            List<String> potentialDeadlocks = new ArrayList<>();

            // 检查每个锁的存在时间
            for (String lockKey : lockKeys) {
                Long ttl = redisTemplate.getExpire(lockKey, TimeUnit.SECONDS);

                // 如果TTL为-1（永不过期）或者TTL超过了阈值，则认为可能是死锁
                if (ttl != null && (ttl == -1 || ttl > timeThresholdSeconds)) {
                    potentialDeadlocks.add(lockKey);
                    log.info("发现可能的死锁: " + lockKey + ", TTL: " + (ttl == -1 ? "永不过期" : ttl + "秒"));
                }
            }

            return potentialDeadlocks;
        } catch (Exception e) {
            log.error("查找死锁异常", e);
            return Collections.emptyList();
        }
    }

    /**
     * 创建分布式锁
     *
     * @param lockKey    锁键
     * @param requestId  请求标识（用于识别锁的持有者）
     * @param expireTime 锁过期时间（秒）
     * @return 是否成功获取锁
     */
    public static boolean acquireLock(String lockKey, String requestId, long expireTime) {
        try {
            String key = getUniqueKey(lockKey);
            // 如果过期时间小于等于0，使用默认超时时间
            if (expireTime <= 0) {
                expireTime = DEFAULT_LOCK_TIMEOUT;
            }

            Boolean result = redisTemplate.opsForValue()
                    .setIfAbsent(key, requestId, expireTime, TimeUnit.SECONDS);
            boolean acquired = Boolean.TRUE.equals(result);

            if (acquired) {
                log.info("成功获取锁，键: " + key + ", 请求ID: " + requestId + ", 过期时间: " + expireTime + "秒");
            } else {
                log.info("获取锁失败，键: " + key + ", 请求ID: " + requestId);
            }

            return acquired;
        } catch (Exception e) {
            log.error("获取锁异常，键: " + lockKey + ", 请求ID: " + requestId, e);
            return false;
        }
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁键
     * @param requestId 请求标识（用于验证锁的持有者）
     * @return 是否成功释放锁
     */
    public static boolean releaseLock(String lockKey, String requestId) {
        try {
            String key = getUniqueKey(lockKey);
            String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "return redis.call('del', KEYS[1]) " +
                    "else " +
                    "return 0 " +
                    "end";

            RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
            Long result = redisTemplate.execute(redisScript, Collections.singletonList(key), requestId);

            boolean released = result != null && result == 1;
            if (released) {
                log.info("成功释放锁，键: " + key + ", 请求ID: " + requestId);
            } else {
                log.info("释放锁失败，键: " + key + ", 请求ID: " + requestId);
            }
            return released;
        } catch (Exception e) {
            log.error("释放锁异常，键: " + lockKey + ", 请求ID: " + requestId, e);
            return false;
        }
    }

//    /**
//     * 添加缓存并注册键
//     *
//     * @param category 缓存类别
//     * @param key 键
//     * @param value 值
//     * @return 是否成功
//     */
//    public static boolean setWithRegistry(String category, String key, Object value) {
//        boolean setResult = set(key, value);
//        if (setResult) {
//            CacheKeyRegistry.registerKey(category, key);
//        }
//        return setResult;
//    }
//
//    /**
//     * 删除缓存并从注册表中移除
//     *
//     * @param category 缓存类别
//     * @param key 键
//     * @return 是否成功
//     */
//    public static boolean deleteWithRegistry(String category, String key) {
//        boolean deleteResult = delete(key);
//        if (deleteResult) {
//            CacheKeyRegistry.unregisterKey(category, key);
//        }
//        return deleteResult;
//    }
//
//    /**
//     * 删除类别下的所有缓存
//     *
//     * @param category 缓存类别
//     * @return 删除的键数量
//     */
//    public static long deleteCategory(String category) {
//        Set<String> keys = CacheKeyRegistry.getKeys(category);
//        long deleteCount = batchDelete(keys);
//        // 清理注册表
//        String registryKey = CacheKeyRegistry.KEY_SET_PREFIX + category;
//        JupiterCacheUtil.delete(registryKey);
//        return deleteCount;
//    }

}
