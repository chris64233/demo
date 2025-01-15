package com.example.demo.dictionary;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.util.NameTransformer;

import java.util.List;

/**
 * @author fands
 * @Title: DictBeanSerializerModifier
 * @Package com.zmdms.common.jackson
 * @Description: 自定义装饰器
 * @date 2024/5/10 14:13
 * @Version 1.0.0
 */
public class DictBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        // 1. 创建新的属性集合
        List<BeanPropertyWriter> newBeanProperties = CollUtil.newArrayList(beanProperties);
        // 2. 循环所有属性
        for (BeanPropertyWriter propertyWriter : beanProperties) {
            Dictionary annotation = propertyWriter.getAnnotation(Dictionary.class);
            if (annotation == null) {
                annotation = propertyWriter.getContextAnnotation(Dictionary.class);
            }
            if (annotation != null) {
                NameTransformer transformer = NameTransformer.simpleTransformer("", annotation.suffix());
                BeanPropertyWriter newProperty = propertyWriter.rename(transformer);
                Class<? extends DicCommonEnum> dicCommonEnum = annotation.dicEnum();
                String dicKey = annotation.dicKey();
                newProperty.assignSerializer(new DictJsonSerializer(dicKey, dicCommonEnum));
                newBeanProperties.add(newProperty);
            }
        }
        return newBeanProperties;
    }

}
