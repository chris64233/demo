package com.example.demo.freemarker.txt;

public abstract class SWIFTMessage {
    protected String referenceNumber; // 交易参考号
    protected String currencyPair;    // 货币对
    protected double amount;          // 交易金额
    protected String senderBank;      // 发送方银行
    protected String receiverBank;   // 接收方银行

    public abstract String generateMessage(); // 生成报文
}
