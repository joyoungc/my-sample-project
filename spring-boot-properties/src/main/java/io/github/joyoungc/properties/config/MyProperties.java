package io.github.joyoungc.properties.config;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Component
@ConfigurationProperties
public class MyProperties {
	
	private InetAddress remoteAddress;
	
	private boolean enabled;
	
//	private final Security security = new Security();
	private Security security;
	
//	private final List<MyPojo> list = new ArrayList<>();
	private List<MyPojo> list;
	
	private List<Map<String, Object>> map;
	
	@Setter @Getter
	public static class Security {
		private String username;
		private List<String> roles;
	}
	
	@Setter @Getter
	public static class MyPojo {
		private String name;
		private String desc;
	}

}
