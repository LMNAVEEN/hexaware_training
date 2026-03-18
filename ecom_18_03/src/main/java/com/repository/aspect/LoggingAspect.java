package com.repository.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Runs BEFORE any method in repository package
    @Before("execution(* com.cart.repository.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[LOG] Starting : " + joinPoint.getSignature().getName());
    }

    // Runs AFTER any method in repository package
    @After("execution(* com.cart.repository.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[LOG] Finished : " + joinPoint.getSignature().getName());
    }
}