package com.example.demo.redis;

import com.example.demo.common.ActivityModuleTypeEnum;
import com.example.demo.domain.ActivityBO;
import com.example.demo.domain.ActivityModuleBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.demo.common.Constants.*;

@RestController
@RequestMapping("/redis")
public class RedisTest {

    @Autowired
    RedisService redisService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/test")
    public boolean redis() {
        return redisService.saveIfAbsentWithTimeOut("redisTest", "222", 1, TimeUnit.MINUTES);
    }

    @GetMapping("/hmset")
    public void redis2() {
        Map<String, Object> cacheMap = new HashMap<>();
        ActivityModuleBO<String> bo1 = new ActivityModuleBO<>();
        bo1.setCode("ActivityModuleBO1");
        bo1.setType(ActivityModuleTypeEnum.ANNOUNCEMENT);
        bo1.setExt("aaa");
        ActivityModuleBO<String> bo2 = new ActivityModuleBO<>();
        bo2.setCode("ActivityModuleBO1");
        bo2.setType(ActivityModuleTypeEnum.BASE);
        bo2.setExt("bbb");
        List<ActivityModuleBO<?>> list = new ArrayList<>();
        list.add(bo1);
        list.add(bo2);
        ActivityBO activity = new ActivityBO();
        activity.setId(1L);
        activity.setCode("111");
        activity.setName("celine");
        activity.setActivityModules(list);
        cacheMap.put(DEFAULT_LANGUAGE, activity);
        cacheMap.put(TEST_LANGUAGE, activity);
        redisService.hmSet(String.format(ACTIVITY_CACHE_KEY, "111"), cacheMap);
    }

    @GetMapping("/hmAll")
    public void redis3() {
        Map<String, ActivityBO> stringActivityBOMap = redisService.hmAll(String.format(ACTIVITY_CACHE_KEY, "111"), ActivityBO.class, "");
        redisService.hmSet(String.format(ACTIVITY_CACHE_KEY, "111-copy"), stringActivityBOMap, "");
        System.out.println(stringActivityBOMap);
    }
}
