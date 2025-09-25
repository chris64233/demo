package com.example.demo.hutool;

import cn.hutool.extra.validation.ValidationUtil;
import com.example.demo.domain.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hutool")
public class HutoolTest {

    @PostMapping("/test")
    public String hello(Student student) {
        ValidationUtil.validate(student);
        return "hello world";
    }
}
