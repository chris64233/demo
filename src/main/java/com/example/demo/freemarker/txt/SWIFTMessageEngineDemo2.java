package com.example.demo.freemarker.txt;

public class SWIFTMessageEngineDemo2 {
    public static void main(String[] args) {
        // 创建 TransactionDetails 对象
        TransactionDetail details = new TransactionDetail();
        details.setReferenceNumber("REF123456");
        details.setCurrencyPair("EUR/USD");
        details.setAmount(1000.0);
        details.setSenderBank("BANKBEBBAXXX");
        details.setReceiverBank("BANKUS33XXX");
        details.setExchangeRate(1.2000);
        details.setIncludeNewt(false);

        // 生成 MT300 报文
        MT300Message2 mt300Message = new MT300Message2(details);
        System.out.println(mt300Message.generateMessage());
    }
}