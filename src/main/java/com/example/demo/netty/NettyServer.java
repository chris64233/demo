package com.example.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {

    /**
     * netty启动端口号
     */
    private static int port = 8080;

    public static void main(String[] args) {
        /**
         *  客户端创建两个线程池组分别为 boss线程组和工作线程组
         */
        // 用于接受客户端连接的请求 （并没有处理请求）
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 用于处理客户端连接的读写操作
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        // 用于创建我们的ServerBootstrap
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // LineBasedFrameDecoder解决粘包-解包问题，设置我们分割最大长度为1024
                        // 原理就是自动帮我们把带有\n或\r\n的数据进行换行
                        //socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        socketChannel.pipeline().addLast(new StringEncoder());// String编码器
                        socketChannel.pipeline().addLast(new StringDecoder());// String解码器
                        socketChannel.pipeline().addLast(new ServerHandler());// 管道类-接收发送消息
                    }
                });
        //  绑定我们的端口号码
        try {
            // 绑定端口号，同步等待成功
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("服务器启动成功：" + port);
            // 等待服务器监听端口
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // 优雅的关闭连接
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}

