package io.github.joyoungc.netty.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

@Component
public class NettyServer {
	
    @Value("${netty.tcp.port}")
    private int tcpPort;
 
    @Value("${netty.boss.thread.count}")
    private int bossCount;
 
    @Value("${netty.worker.thread.count}")
    private int workerCount;
    
    @Value("${netty.so.keepalive}")
    private boolean isKeepAlive;
    
    @Value("${netty.so.backlog}")
    private int backLog;
    
    private static final ChatServerHandler SERVICE_HANDLER = new ChatServerHandler();
    // private static final ServerHandler SERVICE_HANDLER = new ServerHandler();
    
    public void start() {
    	
		// server 환경설정
		EventLoopGroup bossGroup = new NioEventLoopGroup(bossCount);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {

			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
						pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
						pipeline.addLast(new LoggingHandler(LogLevel.INFO));
						pipeline.addLast(SERVICE_HANDLER);
					}					
				})				
				.option(ChannelOption.SO_BACKLOG, backLog)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture channelFuture = b.bind(tcpPort).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e){
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
    	
    }
    
    public void sendMessageToChannels(String msg) {
    	ChannelGroup channels =	SERVICE_HANDLER.getChannels();
		for (Channel c : channels) {
			c.writeAndFlush("[broadCast] " + msg + '\n');
		}
    }
	
}
