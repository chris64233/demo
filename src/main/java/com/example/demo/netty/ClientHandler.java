package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangchi
 * @date 2024/8/1
 * <p>
 * Description:
 */
@Slf4j
class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

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
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        int length = byteBuf.readableBytes();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        String msg = new String(bytes);
        System.out.println("客户端接收消息：" + msg);
    }
}
