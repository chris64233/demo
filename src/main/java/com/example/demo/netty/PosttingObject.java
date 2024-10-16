package com.example.demo.netty;


import io.netty.channel.nio.NioEventLoopGroup;
import lombok.Data;

/**
 * @title PosttingObject 记录netty连接
 * @description
 * @author qizhentao
 * @date 2021-12-23 11:14
 */
@Data
public class PosttingObject {

    private NettyClient nettyClient;

    private NioEventLoopGroup nioEventLoopGroup;

}