package com.example.demo.design.chain.domain;

import lombok.Data;

/**
 * TODO
 *
 * @author yangchi
 * @date 2025/9/23
 */
@Data
public class Cashflow {

    private String tradeType;    // 交易类型
    private double amount;       // 金额
    private double fee;          // 手续费
    private boolean needRiskCheck; // 是否需要风控
    private boolean needSendMsg;   //
}
