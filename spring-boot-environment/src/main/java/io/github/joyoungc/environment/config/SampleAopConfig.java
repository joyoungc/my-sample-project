package io.github.joyoungc.environment.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
public class SampleAopConfig {
	
	@Pointcut("execution(* io.github.joyoungc.environment.util.SampleAopUtils.*(..))")
	public void samplePointcut() {	
		
	}
	
	@Pointcut("@within(org.springframework.stereotype.Controller) "
			+ "|| @within(org.springframework.stereotype.Repository)"
			+ "|| @within(org.springframework.stereotype.Service)")
	public void springBeanPointcut() {
		
	}
	
	@After("springBeanPointcut()")
	public void testSpringBeanlogging() {
		System.out.println("########## AOP 로깅 : springBeanPointcut() ##########");
	}
	
	@After("samplePointcut()")
	public void testSamplePointcutlogging() {
		System.out.println("########## AOP 로깅 : samplePointcut() ##########");
	}
	

}
