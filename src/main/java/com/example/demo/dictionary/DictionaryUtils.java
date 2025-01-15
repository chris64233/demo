package com.example.demo.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fands
 * @Title: DictionaryUtils
 * @Package com.zmdms.common.utils
 * @Description: 字典获取工具类
 * @date 2024/4/28 15:40
 * @Version 1.0.0
 */
@Slf4j
public class DictionaryUtils {

    public static Map<String, Map<String, String>> dataMap = new ConcurrentHashMap<>();

    /**
     * 左边中括号
     */
    public static final String LEFT_CONC = "[";
    /**
     * 右边中括号
     */
    public static final String RIGHT_CONC = "]";

    public static final String DIC_PREFIXX = "dic.";
    public static final String REMARK = "remark";

    /**
     * 启动加载字典数据
     */
    @PostConstruct
    public void init() {
        try {
            YamlPropertiesFactoryBean yamlMapFactoryBean = new YamlPropertiesFactoryBean();
            yamlMapFactoryBean.setResources(new ClassPathResource("dictionary.yml"));
            Properties properties = yamlMapFactoryBean.getObject();

            Enumeration<String> propertyNames = (Enumeration<String>) properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String propertyName = propertyNames.nextElement();
                String dataKey = this.getDataKey(propertyName);
                Map<String, String> dicMap = dataMap.get(dataKey);
                if (null == dicMap) {
                    dicMap = new LinkedHashMap<>();
                }
                String dicKey = this.getDicKey(propertyName);
                dicMap.put(dicKey, properties.getProperty(propertyName));
                dataMap.put(dataKey, dicMap);
            }
        } catch (Exception e) {
            log.error("系统启动加载字典失败", e);
        }
    }

    /**
     * 返回字典Map
     *
     * @param dicKey
     * @return
     */
    public static Map<String, String> getDicMap(String dicKey) {
        checkPrefix(dicKey);
        Map<String, String> dicMap = dataMap.get(dicKey);
        dicMap.remove(REMARK);
        return dicMap;
    }

    /**
     * 返回字典项
     *
     * @param dicKey
     * @param itemKey
     * @return
     */
    public static String getDicValue(String dicKey, String itemKey) {
        checkPrefix(dicKey);
        Map<String, String> dicMap = dataMap.get(dicKey);
        if (CollectionUtils.isEmpty(dicMap)) {
            return null;
        }
        return dicMap.get(itemKey);
    }

    /**
     * 获取所有字典的备注信息
     *
     * @return
     */
    public static Map<String, String> getDicRemark() {
        Map<String, String> remarks = new HashMap<>();
        for (String dicKey : dataMap.keySet()) {
            String remark = getDicValue(dicKey, REMARK);
            if (StringUtils.isNotBlank(remark)) {
                remarks.put(dicKey, remark);
            }
        }
        return remarks;
    }

    /**
     * 检查key的前缀
     *
     * @param key
     */
    private static void checkPrefix(String key) {
        if (!key.startsWith(DIC_PREFIXX)) {
            throw new DictionaryException("dic prefix invoid");
        }
    }


    /**
     * 获取字典数据key 格式：dic.bank-code[0].01020000，取[符号以前的字符为key
     *
     * @param propertyName
     * @return
     */
    private String getDataKey(String propertyName) {
        if (propertyName.contains(LEFT_CONC)) {
            return propertyName.substring(0, propertyName.indexOf(LEFT_CONC));
        }
        return propertyName;
    }

    /**
     * 获取字典key
     *
     * @param propertyName
     * @return
     */
    private String getDicKey(String propertyName) {
        if (propertyName.contains(RIGHT_CONC)) {
            return propertyName.substring(propertyName.indexOf(RIGHT_CONC) + 2);
        }
        return null;
    }
}


