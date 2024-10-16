package com.example.demo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author yangchi
 * @date 2024/8/1
 * <p>
 * Description:
 */
class ClientHandler2 extends SimpleChannelInboundHandler<String> {

    public ChannelHandlerContext channelHandlerContext;

    /**
     * 活跃通道可以发送消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelHandlerContext = ctx;
        //channelHandlerContext.writeAndFlush("11111");
        //channelHandlerContext.channel().close();
    }

    /**
     * 读取消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("接收消息：" + msg);
        //channelHandlerContext.channel().close();
    }
}
