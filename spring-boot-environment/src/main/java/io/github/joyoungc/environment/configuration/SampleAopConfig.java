package io.github.joyoungc.environment.configuration;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP Configuration
 *
 */
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

    @AfterReturning("springBeanPointcut()")
    public void testSpringBeanlogging() {
        System.out.println("########## AOP 로깅 : springBeanPointcut() ##########");
    }

    @AfterThrowing("samplePointcut()")
    public void testSamplePointcutlogging() {
        System.out.println("########## AOP 로깅 : samplePointcut() ##########");
    }


}
