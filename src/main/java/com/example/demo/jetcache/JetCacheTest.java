package com.example.demo.jetcache;

import com.example.demo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jetCache")
public class JetCacheTest {

    @Autowired
    CacheService cacheService;

    @GetMapping("/test")
    public Student jetCache(@RequestParam int id) {
        return cacheService.get(id);
    }}
