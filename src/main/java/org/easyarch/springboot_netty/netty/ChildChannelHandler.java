package org.easyarch.springboot_netty.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName ChildChannelHandler
 * @Description 子Handler处理 加上http编解码和自定义Handler  同时注入IOC
 * @Author Liyihe
 * @Date 2019/09/15 下午11:24
 * @Version 1.0
 */
@Component
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Autowired
    private NettyServerHandler discardServerHandler;

    public void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new HttpResponseEncoder());//server端发送的是httpResponse,要进行编码
        socketChannel.pipeline().addLast(new HttpRequestDecoder());//server端接收的是httpRequest,要进行解码
        socketChannel.pipeline().addLast(new HttpObjectAggregator(65535));
        socketChannel.pipeline().addLast(discardServerHandler);
    }
}