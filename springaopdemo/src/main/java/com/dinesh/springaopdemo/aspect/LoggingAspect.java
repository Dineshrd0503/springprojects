package com.dinesh.springaopdemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(*com.dinesh.springaopdemo.service.BankService.*(..))")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("[LOG] before method:"+joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.dinesh.springaopdemo.service.BankService.*(..))",returning ="result")
    public void logAfter(JoinPoint joinPoint,Object result) {
        System.out.println("[LOG] after method:"+joinPoint.getSignature().getName()+" "+"result: "+result);

    }

    @AfterThrowing(value = "execution(* com.dinesh.springaopdemo.service.BankService.*(..))",throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint,Throwable ex) {
        System.out.println("[LOG] method:" + joinPoint.getSignature().getName() + " " + "threw " + ex.getLocalizedMessage());
    }

    @After("execution(* com.dinesh.springaopdemo.service.BankService.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[LOG] after method:" + joinPoint.getSignature().getName());

    }
    }

