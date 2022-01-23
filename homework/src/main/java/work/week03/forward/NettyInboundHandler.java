package work.week03.forward;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


public class NettyInboundHandler extends ChannelInboundHandlerAdapter {


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
