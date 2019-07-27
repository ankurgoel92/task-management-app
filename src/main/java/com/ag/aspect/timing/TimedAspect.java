package com.ag.aspect.timing;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TimedAspect {

    private static final Logger LOG = LoggerFactory.getLogger(TimedAspect.class);

    @Around("execution (@com.ag.aspect.timing.Timed * *.*(..))")
    public Object timedMethod(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object output = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        LOG.info("Method {} of Class {} took {}ms ", method.getName(), method.getDeclaringClass().getSimpleName(), elapsedTime);
        return output;
    }

}