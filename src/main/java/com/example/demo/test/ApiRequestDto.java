package com.example.demo.test;

import lombok.Data;

/**
 * @author yangchi
 * @date 2024/6/13
 * <p>
 * Description:
 */
@Data
public class ApiRequestDto {
    private String appId;
    private Long timestamp;
    private String biz;
}
