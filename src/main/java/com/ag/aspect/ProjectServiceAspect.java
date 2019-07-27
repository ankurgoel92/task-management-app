package com.ag.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class ProjectServiceAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectServiceAspect.class);

    @Before("execution(* findById(long))")
    public void before(JoinPoint joinPoint) {
        LOG.info("Executing {} with args {}", joinPoint.getSignature(), joinPoint.getArgs()[0]);
    }

}