package com.example.demo.netty.cabinet;

import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author fands
 * @Title: CabinerClient
 * @Package com.zmdms.server
 * @Description: 电柜客户端，测试
 * @date 2024/10/29 14:53
 * @Version 1.0.0
 */
public class CabinerClient {

    private String ip;

    private int port;

    private boolean stop = false;

    public CabinerClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() throws IOException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>(){

            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast("decoder", new JsonObjectDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("chat", new ClientHandler());
            }
        });
        try {
            //连接服务
            Channel channel = bootstrap.connect(ip, port).sync().channel();
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String content = reader.readLine();
                if (StrUtil.isNotEmpty(content)) {
                    if (StrUtil.equalsIgnoreCase(content, "q")) {
                        System.exit(1);
                    }
                    channel.writeAndFlush(content);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static class ClientHandler extends SimpleChannelInboundHandler<String> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
            //打印服务端的发送数据
            System.out.println(s);
        }

    }
    public static void main(String[] args) throws IOException {
        new CabinerClient("127.0.0.1", 11999).run();
    }
}
