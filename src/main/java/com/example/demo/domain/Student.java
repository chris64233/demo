package com.example.demo.domain;

import com.example.demo.dictionary.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private int id;
    private int age;

    @Pattern(regexp = "^(A|B)$", message = "名称只能为A或B")
    private String name;
    private boolean isAdult;

    @Dictionary(dicKey = "dic.sex")
    private Integer sex;
}
