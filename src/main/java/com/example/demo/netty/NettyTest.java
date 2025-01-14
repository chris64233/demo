package com.example.demo.netty;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/netty")
public class NettyTest {

    public static final String USER_ID = "1";

//    private static final String host = "localhost";
//    private static final int port = 11999;

//    private static final String host = "36.139.37.107";
//    private static final int port = 3444;
    private static final String host = "36.156.153.249";
    private static final int port = 9006;



    @RequestMapping("/login")
    public String test() {
        // 初始化Netty连接
        new NettyClient().initNetty(USER_ID, host, port);

        // 根据用户id发送消息到客户端
        PosttingObject posttingObject = NettyClient.concurrentHashMap.get(USER_ID);
        try{
            // 发送消息
            NettyClient nettyClient = posttingObject.getNettyClient();
//            nettyClient.clientHandler.channelHandlerContext.writeAndFlush("01\n");
//            nettyClient.clientHandler.channelHandlerContext.writeAndFlush("02\n");
//            nettyClient.clientHandler.channelHandlerContext.writeAndFlush("03\n");
            nettyClient.clientHandler.channelHandlerContext.writeAndFlush("{\"msgType\":110,\"imsi\":\"0123456\",\"batteryStatus\":\"0\",\"hardVersion\":\"\",\"softVersion\":\"\",\"devId\":\"BCCC123456\",\"protocolVersion\":\"V1\",\"devType\":1,\"txnNo\":1733465895505}");
        } finally {
            // 优雅关闭连接
//            posttingObject.getNioEventLoopGroup().shutdownGracefully();
//            System.out.println("关闭连接");
        }
        return "hello world";
    }

    @RequestMapping("/attr")
    public String attr() {
        // 初始化Netty连接
        new NettyClient().initNetty(USER_ID, host, port);

        // 根据用户id发送消息到客户端
        PosttingObject posttingObject = NettyClient.concurrentHashMap.get(USER_ID);
        // 发送消息
        NettyClient nettyClient = posttingObject.getNettyClient();
        String jsonString = "{\"devId\":\"BCCC123456\",\"txnNo\":\"1733887908732\",\"msgType\":310,\"attrList\":[{\"id\":\"01101001\",\"value\":\"5\"},{\"id\":\"01102001\",\"value\":\"113.948639\"},{\"id\":\"01103001\",\"value\":\"22.570526\"},{\"id\":\"01109001\",\"value\":\"0\"},{\"id\":\"01110001\",\"value\":\"509\"}]}";
        nettyClient.clientHandler.channelHandlerContext.writeAndFlush(jsonString);
        return "hello world";
    }

    @RequestMapping("/alarm")
    public String alarm() {
        // 初始化Netty连接
        new NettyClient().initNetty(USER_ID, host, port);

        // 根据用户id发送消息到客户端
        PosttingObject posttingObject = NettyClient.concurrentHashMap.get(USER_ID);
        // 发送消息
        NettyClient nettyClient = posttingObject.getNettyClient();
        String jsonString = "{\"devId\":\"BT107202012MT00221012566\",\"txnNo\":\"1723529980003\",\"msgType\":410,\"alarmList\":[{\"alarmDesc\":\"02\",\"alarmTime\":1723529980002,\"id\":\"01002001\",\"alarmFlag\":1},{\"alarmDesc\":\"10\",\"alarmTime\":1723529980002,\"id\":\"01002001\",\"alarmFlag\":0},{\"alarmDesc\":\"11\",\"alarmTime\":1723529980002,\"id\":\"01002001\",\"alarmFlag\":0}]}";
        nettyClient.clientHandler.channelHandlerContext.writeAndFlush(jsonString);
        return "hello world";
    }


    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
