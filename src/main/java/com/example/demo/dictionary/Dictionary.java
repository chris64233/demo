package com.example.demo.dictionary;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fands
 * @Title: Dictionary
 * @Package com.zmdms.common.jackson
 * @Description: 字典序列化注解
 * @date 2024/5/10 13:53
 * @Version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@JacksonAnnotationsInside
public @interface Dictionary {

    /**
     * 字典使用字典key
     */
    String dicKey() default "";

    /**
     * 字典使用枚举
     */
    Class<? extends DicCommonEnum> dicEnum() default Empty.class;

    /**
     * 指定后缀
     * 翻译后的属性名称：添加了注解的属性名+指定后缀
     */
    String suffix() default "Text";

}
