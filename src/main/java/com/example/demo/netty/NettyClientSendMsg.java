package com.example.demo.netty;

/**
 * @title NettyClientSendMsg
 * @description
 * @author qizhentao
 * @date 2021-12-23 11:03
 */
public class NettyClientSendMsg {

    private static final String host = "localhost";
    private static final int port = 8080;

    public static void main(String[] args) {
        // 初始化Netty连接
        String userId = "1";
        new NettyClient().initNetty(userId, host, port);

        // 根据用户id发送消息到客户端
        PosttingObject posttingObject = NettyClient.concurrentHashMap.get(userId);
        try{
            // 发送消息
            NettyClient nettyClient = posttingObject.getNettyClient();
            nettyClient.clientHandler.channelHandlerContext.writeAndFlush("01\n");
            nettyClient.clientHandler.channelHandlerContext.writeAndFlush("02\n");
            nettyClient.clientHandler.channelHandlerContext.writeAndFlush("03\n");
        }finally {
            // 优雅关闭连接
            posttingObject.getNioEventLoopGroup().shutdownGracefully();
        }
    }


}