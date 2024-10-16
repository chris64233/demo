package com.example.demo.appConfig;

import com.example.demo.config.MoveConfig;
import com.example.demo.util.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/appConfig")
public class appConfigTest {

    @Autowired
    MoveConfig moveConfig;

    @RequestMapping("/test")
    public void test() {
        String mcActivityMaxUpdateTime = moveConfig.getMcActivityMaxUpdateTime();
        boolean switched = moveConfig.isSwitched();
        System.out.println(mcActivityMaxUpdateTime);
        System.out.println(switched);
        LocalDateTime mcActivityMaxUpdateTimeLdt = LocalDateTimeUtil.toLocalDateTime(mcActivityMaxUpdateTime);
        System.out.println(mcActivityMaxUpdateTimeLdt);
    }
}
