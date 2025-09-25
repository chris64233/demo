package com.example.demo.hutool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO
 *
 * @author yangchi
 * @date 2025/4/15
 */
@Data
@AllArgsConstructor
public class MessageContent {

    /**
     * 阈
     */
    private String domain;

    /**
     * 值
     */
    private String value;
}
