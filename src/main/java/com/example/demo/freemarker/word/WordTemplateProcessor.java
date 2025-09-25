package com.example.demo.freemarker.word;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordTemplateProcessor {

    public static void processWordTemplate(String templatePath, String outputPath, Map<String, Object> dataModel) {
        File file = new File(templatePath);
        if (!file.exists()) {
            throw new RuntimeException("❌ 文件不存在：" + templatePath);
        }

        try {
            // 获取文件扩展名
            String extension = getFileExtension(file);

            if ("docx".equalsIgnoreCase(extension)) {
                processDocx(templatePath, outputPath, dataModel);
            } else if ("doc".equalsIgnoreCase(extension)) {
                processDoc(templatePath, outputPath, dataModel);
            } else {
                throw new RuntimeException("❌ 不支持的文件格式：" + extension);
            }

            System.out.println("✅ Word 模板处理完成，已生成：" + outputPath);
        } catch (Exception e) {
            throw new RuntimeException("❌ 处理 Word 模板失败", e);
        }
    }

    // 处理 docx（新格式）
    private static void processDocx(String templatePath, String outputPath, Map<String, Object> dataModel) throws Exception {
        FileInputStream fis = new FileInputStream(templatePath);
        XWPFDocument document = new XWPFDocument(fis);

        for (XWPFParagraph paragraph : document.getParagraphs()) {
            replaceText(paragraph, dataModel);
        }
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        replaceText(paragraph, dataModel);
                    }
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            document.write(fos);
        }
    }

    // 处理 doc（老格式）
    private static void processDoc(String templatePath, String outputPath, Map<String, Object> dataModel) throws Exception {
        FileInputStream fis = new FileInputStream(templatePath);
        HWPFDocument document = new HWPFDocument(fis);

        Range range = document.getRange();
        String text = range.text();
        String newText = processTemplate(text, dataModel);

        range.replaceText(text, newText);

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            document.write(fos);
        }
    }

    // 替换变量
    private static void replaceText(XWPFParagraph paragraph, Map<String, Object> dataModel) {
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null) {
                String newText = processTemplate(text, dataModel);
                run.setText(newText, 0);
            }
        }
    }

    // 使用 FreeMarker 替换变量
    private static String processTemplate(String templateString, Map<String, Object> dataModel) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setDefaultEncoding("UTF-8");

            StringWriter writer = new StringWriter();
            Template template = new Template("wordTemplate", new StringReader(templateString), cfg);
            template.process(dataModel, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("❌ FreeMarker 解析失败", e);
        }
    }

    // 获取文件扩展名
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static void main(String[] args) {
        String templatePath = "C:\\Users\\Administrator\\Desktop\\test\\word_template.doc"; // 兼容 doc 和 docx
        String outputPath = "C:\\Users\\Administrator\\Desktop\\test\\processed_word.doc";

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("name", "张三");
        dataModel.put("date", "2025-03-24");
        dataModel.put("amount", "1000.00");

        processWordTemplate(templatePath, outputPath, dataModel);
    }
}
