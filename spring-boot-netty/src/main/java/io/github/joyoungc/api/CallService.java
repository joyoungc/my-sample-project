package io.github.joyoungc.api;

import org.springframework.stereotype.Service;

import io.github.joyoungc.netty.server.NettyServer;

@Service
public class CallService {
	
	private final NettyServer nettyServer;
	
	public CallService(NettyServer nettyServer) {
		this.nettyServer = nettyServer;
	}

	public void callUser(String msg) {
		nettyServer.sendMessageToChannels(msg);
	}
	
}
