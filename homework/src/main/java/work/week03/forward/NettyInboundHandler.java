package work.week03.forward;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;
import java.net.URI;

public class NettyInboundHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx){
        try {
            System.out.println(">>>>>>>>>>");
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
            request.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json").set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE).set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes()).set(HttpHeaderNames.HOST,"localhost");
            ctx.write(request);
            ctx.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(">>>>>>");
        try {
            if (msg instanceof HttpResponse) {
                FullHttpResponse response = (FullHttpResponse) msg;
                ByteBuf content = response.content();
                System.out.println("response: " + content.toString(CharsetUtil.UTF_8));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
