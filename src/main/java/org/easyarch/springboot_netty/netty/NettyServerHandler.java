package org.easyarch.springboot_netty.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import org.easyarch.springboot_netty.service.CentralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @ClassName NettyServerHandler
 * @Description 自定义Handler 同时注入IOC
 * 同时加上@ChannelHandler.Sharable
 * 因为这个类被注入IOC(成为单例) 那么加上这个注解达到同时多个请求共享一个ChannelHandler(不加上这个注解那么每次来个)
 * 但是不保证线程安全 但是本类中只有一个全局变量是  CentralService centralService
 * 该变量是从IOC自动装配(单列) 该类CentralService(Service修饰)中没有全局变量 那么不存在线程不安全的问题
 * 如果NettyServerHandler 且被@Sharable注解修饰 有全局变量(可能会导致线程不安全的全局变量) 那么需要开发者自行解决线程安全问题
 * @Author Liyihe
 * @Date 2019/09/15 下午11:24
 * @Version 1.0
 */
@Component
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Autowired
    private CentralService centralService;
    private boolean aa=false;
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        DefaultFullHttpResponse response = null;
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(centralService.executeServices(request).getBytes(StandardCharsets.UTF_8)));
            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "Authorization, Origin, X-Requested-With, Content-Type, Accept");
            response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }

}
