package com.example.demo.design.chain.controller;

import com.example.demo.design.chain.domain.Cashflow;
import com.example.demo.design.chain.service.ClearService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author yangchi
 * @date 2025/9/23
 */

@RestController
@RequestMapping("/clear")
public class ClearController {

    @Resource
    private ClearService clearService;

    @PostMapping
    public String clear(@RequestBody Cashflow cf) {
        clearService.clear(cf);
        return "交易 " + cf.getTradeType() + " 清算完成";
    }
}
