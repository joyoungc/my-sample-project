package io.github.joyoungc.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import io.github.joyoungc.properties.config.MyProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootPropertiesApplicationTests {
	
	@Autowired
	MyProperties properties;

	@Test
	public void contextLoads() {
		System.out.println(properties.getRemoteAddress().getHostAddress());
		System.out.println(properties.getSecurity().getUsername());
		
		assertThat(properties.getSecurity().getUsername(), is("admin"));
		assertThat(properties.isEnabled(), is(true));
		assertThat(properties.getSecurity().getRoles(), hasSize(2));
		
		assertThat(properties.getList(), hasSize(1));
		System.out.println(properties.getList().get(0).getName());
		
		System.out.println(properties.getMap().get(0).get("name"));
		
	}

}
