package work.week03.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import work.week03.gateway.filter.HttpRequestFilter;

public class ProxyBizFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest request, ChannelHandlerContext context) {
        request.headers().set("requestHeader", "java");
    }
}
