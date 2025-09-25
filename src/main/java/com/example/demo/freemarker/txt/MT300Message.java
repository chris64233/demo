package com.example.demo.freemarker.txt;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class MT300Message extends SWIFTMessage {
    private double exchangeRate; // 汇率
    private boolean includeNewt; // 是否包含 :22A:NEWT

    public MT300Message(String referenceNumber, String currencyPair, double amount, String senderBank, String receiverBank, double exchangeRate, boolean includeNewt) {
        this.referenceNumber = referenceNumber;
        this.currencyPair = currencyPair;
        this.amount = amount;
        this.senderBank = senderBank;
        this.receiverBank = receiverBank;
        this.exchangeRate = exchangeRate;
        this.includeNewt = includeNewt;
    }

    @Override
    public String generateMessage() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassLoaderForTemplateLoading(Thread.currentThread().getContextClassLoader(), "swifttemplates");
        cfg.setDefaultEncoding("UTF-8");

        try {
            // 加载模板
            Template template = cfg.getTemplate("MT300_TXT.ftl");

            // 准备数据
            Map<String, Object> data = new HashMap<>();
            data.put("referenceNumber", referenceNumber);
            data.put("currencyPair", currencyPair);
            data.put("amount", amount);
            data.put("exchangeRate", exchangeRate);
            data.put("senderBank", senderBank);
            data.put("receiverBank", receiverBank);
            data.put("includeNewt", includeNewt); // 是否包含 :22A:NEWT

            // 渲染模板
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException("Failed to generate MT300 message", e);
        }
    }
}