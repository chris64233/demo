//package com.example.demo.webflux;
//
//import org.springframework.http.codec.CodecConfigurer;
//import org.springframework.http.codec.HttpMessageReader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.server.HandlerStrategies;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RestController
//@RequestMapping("/webflux")
//public class WebfluxTest {
//
//    @Resource
//    CodecConfigurer configurer;
//
//    @RequestMapping("/getReader")
//    public List<HttpMessageReader<?>> getReader() {
//        List<HttpMessageReader<?>> readers = configurer.getReaders();
//        List<HttpMessageReader<?>> httpMessageReaders = HandlerStrategies.withDefaults().messageReaders();
//        System.out.println(readers);
//        System.out.println(httpMessageReaders);
//        return readers;
//    }
//}
