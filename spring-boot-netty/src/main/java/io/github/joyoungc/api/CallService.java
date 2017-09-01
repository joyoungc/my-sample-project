package io.github.joyoungc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.joyoungc.netty.server.NettyServer;

@Service
public class CallService {
	
	@Autowired
	NettyServer nettyServer;
	
	public void callUser(String msg) {
		nettyServer.sendMessageToChannels(msg);
	}
	
}
