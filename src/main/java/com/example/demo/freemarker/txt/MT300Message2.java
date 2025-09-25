package com.example.demo.freemarker.txt;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

public class MT300Message2 extends SWIFTMessage {
    private TransactionDetail detail; // 使用对象作为数据模型

    public MT300Message2(TransactionDetail detail) {
        this.detail = detail;
    }

    @Override
    public String generateMessage() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassLoaderForTemplateLoading(Thread.currentThread().getContextClassLoader(), "swifttemplates");
        cfg.setDefaultEncoding("UTF-8");

        try {
            // 加载模板
            Template template = cfg.getTemplate("MT300_TXT.ftl");

            // 渲染模板
            StringWriter writer = new StringWriter();
            template.process(detail, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException("Failed to generate MT300 message", e);
        }
    }
}