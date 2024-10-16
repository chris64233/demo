package com.example.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    /**
     * 存储用户id和netty连接关系
     */
    public static ConcurrentHashMap<String, PosttingObject> concurrentHashMap = new ConcurrentHashMap();

    public ClientHandler clientHandler = new ClientHandler();

    public void initNetty(String userId, String host, int port) {
        //创建nioEventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("正在连接中...");
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new JsonObjectDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast("idleStateHandler", new IdleStateHandler(300, 0, 0, TimeUnit.SECONDS));
                        pipeline.addLast("clientHandler", clientHandler);

                    }
                });

        try {
            // 发起连接
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            System.out.println("用户：" + userId + "->服务端连接成功...");
            System.out.println(sync.channel());

            /* // 发送消息
            sync.channel().writeAndFlush(msg);
            System.out.println("消息发送完成");
            // 关闭连接
            sync.channel().close();*/

            // 异步等待关闭连接channel
            // sync.channel().closeFuture().sync();
            // System.out.println("连接已关闭..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //group.shutdownGracefully();
            //System.out.println("优雅关闭连接");
        }

        PosttingObject posttingObject = new PosttingObject();
        posttingObject.setNioEventLoopGroup(group);
        posttingObject.setNettyClient(this);
        concurrentHashMap.put(userId, posttingObject);
    }

}


