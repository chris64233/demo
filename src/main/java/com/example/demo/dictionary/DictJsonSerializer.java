package com.example.demo.dictionary;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @author fands
 * @Title: DictJsonSerializer
 * @Package com.zmdms.common.jackson
 * @Description: 自定义序列化器
 * @date 2024/5/10 14:01
 * @Version 1.0.0
 */
public class DictJsonSerializer extends StdSerializer<Object> {

    private String dicKey;
    private Class<? extends DicCommonEnum> dicClazz;
    protected DictJsonSerializer() {
        super(Object.class);
    }

    protected DictJsonSerializer(String dicKey) {
        super(Object.class);
        this.dicKey = dicKey;
    }

    protected DictJsonSerializer(Class<? extends DicCommonEnum> dicClazz) {
        super(Object.class);
        this.dicClazz = dicClazz;
    }

    protected DictJsonSerializer(String dicKey, Class<? extends DicCommonEnum> dicClazz) {
        super(Object.class);
        this.dicKey = dicKey;
        this.dicClazz = dicClazz;
    }

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String text = null;
        String itemKey = o.toString();
        if (Empty.class != dicClazz) {
            DicCommonEnum[] enumConstants = dicClazz.getEnumConstants();
            if (null != enumConstants && enumConstants.length > 0) {
                text = enumConstants[0].getText(itemKey);
            }
        }
        if (StringUtils.isBlank(text)) {
            text = DictionaryUtils.getDicValue(dicKey, itemKey);
        }
        jsonGenerator.writeString(text);
    }
}
