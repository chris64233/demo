package com.example.demo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author yangchi
 * @date 2024/8/1
 * <p>
 * Description:
 */
class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("8080-msg:" + msg);
        // 响应内容:
        channelHandlerContext.writeAndFlush("8080\n"); // String类型加上\n会自动粘包和拆包了
    }
}
