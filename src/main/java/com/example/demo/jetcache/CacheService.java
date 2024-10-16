package com.example.demo.jetcache;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.example.demo.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class CacheService {

    @Cached(name = "testJetCache", key = "#id", cacheType = CacheType.BOTH, expire = 1000)
    public Student get(int id) {
        Student s = new Student();
        s.setId(id);
        s.setAge(10);
        s.setName("celine");
        return s;
    }
}
