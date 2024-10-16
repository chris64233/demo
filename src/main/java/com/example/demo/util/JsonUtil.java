package com.example.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p></p>
 *
 * @author znz
 * @author wangwei
 * @version 2018/12/14
 */
@Slf4j
public class JsonUtil {
    private JsonUtil() {
        throw new IllegalStateException("Utility class for JsonUtil");
    }

    public static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);


        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);


        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    }

    public static <T> Optional<String> toJson(T obj) {
        try {
            return Optional.of(objectMapper.writeValueAsString(obj));
        } catch (Exception e) {
            LoggerUtil.error(log, LoggerUtil.LogType.EXCEPTION, "toJson", "{}", e);
            return Optional.empty();
        }
    }

    public static <T> List<T> fromJsonArr(String jsonStr, Class<T> tClass) {

        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
            return objectMapper.readValue(jsonStr, listType);
        } catch (JsonProcessingException e) {
            LoggerUtil.error(log, LoggerUtil.LogType.EXCEPTION, "fromJsonArr", "{}", jsonStr,e);
            return new ArrayList<>();
        }
    }

    public static <T> Map<String, T> fromJsonMap(String jsonStr, Class<T> tClass) {

        try {
            return objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>(){});
        } catch (JsonProcessingException e) {
            LoggerUtil.error(log, LoggerUtil.LogType.EXCEPTION, "fromJsonArr", "{}", jsonStr,e);
            return new HashMap<>();
        }
    }

    public static <T> Optional<T> fromJson(String jsonStr, Class<T> tClass) {
        try {
            return Optional.of(objectMapper.readValue(jsonStr, tClass));
        } catch (Exception e) {
            LoggerUtil.error(log, LoggerUtil.LogType.EXCEPTION, "fromJson", "{}", jsonStr, e);
            return Optional.empty();
        }
    }

    public static Optional<JsonNode> getStr2JsonNode(String responseBody) {
        try {
            return Optional.of(objectMapper.readTree(responseBody));
        } catch (Exception e) {
            LoggerUtil.error(log, LoggerUtil.LogType.EXCEPTION, "get response code.error", "{}", e);
        }

        return Optional.empty();
    }
}
