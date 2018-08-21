package io.github.joyoungc.cloud.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

/***
 * 
 * @author joyoungc
 * @date 2018.08.
 *
 */
@Slf4j
@RestController
@RequestMapping("/sleuth")
public class SluethSampleController implements ApplicationListener<ServletWebServerInitializedEvent> {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Tracer tracer;

	Random random = new Random();
	
	private int localPort;

	@GetMapping("/hello")
	public String hello() throws InterruptedException {
		log.info("## hello");
		Random random = new Random();
		Thread.sleep(random.nextInt(1000));
		String result = this.restTemplate.getForObject("http://localhost:" + this.localPort + "/sleuth/there",
				String.class);
		return "hello " + result;
	}

	@GetMapping("/there")
	public String there() throws InterruptedException {
		log.info("## there");
		int millis = this.random.nextInt(1000);
		Thread.sleep(millis);
		this.tracer.currentSpan().tag("random-sleep-millis", String.valueOf(millis));
		return "there";
	}

	@Override
	public void onApplicationEvent(ServletWebServerInitializedEvent event) {
		this.localPort = event.getSource().getPort();
		log.info("## local port : {}", localPort);
	}

}
