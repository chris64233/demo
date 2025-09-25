package com.example.demo.test;

import com.example.demo.domain.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Test {

    @PostMapping("/test")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/dic")
    public Student dic() {
        Student student = new Student();
        student.setSex(0);
        return student;
    }


    public static void main(String[] args) {
        System.out.println(1);

        
    }
}
