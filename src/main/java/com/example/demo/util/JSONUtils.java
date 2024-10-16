package com.example.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JSONUtils {

    private static ObjectMapper mapper;

    static {
        mapper = (ObjectMapper) SpringContextUtils.getBean(ObjectMapper.class);

    }

    public static String serializeObject(Object o) {
        try{
            return mapper.writeValueAsString(o);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String serializeObjectPretty(Object o) {
        try{
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserializeObject(String s, Class<T> clazz) {
        try{
            return mapper.readValue(s, clazz);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserializeObject(String s, TypeReference<T> typeReference){
        try{
            return mapper.readValue(s, typeReference);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserializeObject(InputStream src, TypeReference<T> typeReference){
        try{
            return mapper.readValue(src, typeReference);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> deserializeList(String s, Class<T> clazz){
        try{
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(s, javaType);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T getObject(String jsonStr, String key, Class<T> clazz) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonStr);
            JsonNode value = jsonNode.findValue(key);
            return mapper.readValue(value.toString(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getList(String jsonStr, String key, Class<T> clazz) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonStr);
            JsonNode value = jsonNode.findValue(key);
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(value.toString(), javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readJsonFromClassPath(String path, TypeReference<T> typeReference){

        ClassPathResource resource = new ClassPathResource(path);
        if (resource.exists()) {
            T o = null;
            try(InputStream in = resource.getInputStream()){
                o = mapper.readValue(in, typeReference);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return o;
        } else {
            throw new RuntimeException("找不到文件，path:" + path + "");
        }
    }

}
