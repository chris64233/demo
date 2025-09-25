package com.example.demo.freemarker.txt;

import lombok.Data;

@Data
public class TransactionDetail {
    private String referenceNumber; // 交易参考号
    private String currencyPair;    // 货币对
    private double amount;          // 交易金额
    private String senderBank;      // 发送方银行
    private String receiverBank;    // 接收方银行
    private double exchangeRate;    // 汇率
    private boolean includeNewt;    // 是否包含 :22A:NEWT
    private boolean showOriRefNum;
    private boolean showInter;
    private String indicator;
}
