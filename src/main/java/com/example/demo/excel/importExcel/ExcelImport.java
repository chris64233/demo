package com.example.demo.excel.importExcel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.InputStream;
import java.util.*;

/**
 * @author yangchi
 * @date 2024/10/22
 * <p>
 * Description:
 */
public class ExcelImport {
    public static void main(String[] args) {
        String fileName = "temp/三级渠道导入.xlsx";
        InputStream inputStream = ExcelImport.class.getClassLoader().getResourceAsStream(fileName);

        // 创建 Excel 读取器
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        // 读取所有数据
        List<Map<String, Object>> allData = reader.readAll();
        System.out.println("总行数：" + allData.size());

        // 去重后的列表
        List<Map<String, Object>> deduplicatedData = removeDuplicates(allData, "运营商", "省", "市");

        System.out.println("去重后总行数：" + deduplicatedData.size());

        // 遍历数据
        for (Map<String, Object> rowData : deduplicatedData) {
            // 遍历每一行的数据
            for (Map.Entry<String, Object> entry : rowData.entrySet()) {
                System.out.print(entry.getValue() + "\t");
            }
            System.out.println();
        }

        // 关闭读取器
        reader.close();
    }


    private static List<Map<String, Object>> removeDuplicates(List<Map<String, Object>> data, String... keys) {
        // 使用 HashSet 来存储唯一的键值对组合
        Set<List<Object>> seenCombinations = new HashSet<>();

        // 使用 ArrayList 来存储去重后的数据
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> entry : data) {
            // 提取指定的键对应的值
            List<Object> combination = new ArrayList<>();
            for (String key : keys) {
                combination.add(entry.get(key));
            }

            // 如果集合中还没有这个组合，则添加到结果列表中
            if (!seenCombinations.contains(combination)) {
                seenCombinations.add(combination);
                result.add(entry);
            }
        }

        return result;
    }
}
