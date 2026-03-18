package com.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
public class LogAspect {

    // BEFORE method execution
    @Before("execution(* com.repository.TicketRepository.*(..))")
    public void logBefore(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Start time: " + LocalTime.now());
        System.out.println(">>> Method STARTED: " + methodName);
    }

    // AFTER method execution
    @After("execution(* com.repository.TicketRepository.*(..))")
    public void logAfter(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("End time: " + LocalTime.now());
        System.out.println(">>> Method ENDED: " + methodName);
    }
}