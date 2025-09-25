package com.example.demo.freemarker.xml;

import com.example.demo.freemarker.txt.MT300Message2;
import com.example.demo.freemarker.txt.TransactionDetail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.StringWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MT300XMLGenerator {
    public static String generateMT300XML(TransactionDetail detail) {
        try {
            // 1. 初始化 FreeMarker
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setClassLoaderForTemplateLoading(Thread.currentThread().getContextClassLoader(), "swifttemplates");
            cfg.setDefaultEncoding("UTF-8");

            // 2. 读取 XML 模板
            Template template = cfg.getTemplate("MT300_XML.ftl");

            // 4. 使用 StringWriter 生成 XML 字符串
            StringWriter writer = new StringWriter();
            template.process(detail, writer);
            return writer.toString();  // 返回 XML 字符串
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            return null;  // 发生异常时返回 null
        }
    }

    public static void main(String[] args) {
        // 创建 TransactionDetails 对象
        TransactionDetail detail = new TransactionDetail();
        detail.setReferenceNumber("REF123456");
        detail.setCurrencyPair("EUR/USD");
        detail.setAmount(1000.0);
        detail.setSenderBank("BANKBEBBAXXX");
        detail.setReceiverBank("BANKUS33XXX");
        detail.setExchangeRate(1.2000);
        detail.setIncludeNewt(false);
        detail.setShowOriRefNum(false);
        detail.setShowInter(false);
        detail.setIndicator("NEWT");

        String mt300XML = generateMT300XML(detail);
        System.out.println(mt300XML);  // 直接打印 XML 内容
    }
}
