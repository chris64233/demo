package com.example.demo.exception;

import com.example.demo.domain.Student;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/exception")
public class ExceptionTest {

    @PostMapping("/test")
    public String hello(@Valid @RequestBody Student student) {

        return "hello world";
    }

}
