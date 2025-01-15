package com.example.demo.domain;

import com.example.demo.dictionary.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private int id;
    private int age;
    private String name;
    private boolean isAdult;

    @Dictionary(dicKey = "dic.sex")
    private Integer sex;
}
