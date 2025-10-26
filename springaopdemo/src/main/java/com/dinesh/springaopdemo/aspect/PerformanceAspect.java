package com.dinesh.springaopdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {
    @Around("execution(* com.dinesh.springaopdemo.service.BankService.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint pjp) throws Throwable{
        long start=System.currentTimeMillis();
        Object result=pjp.proceed();
        long end=System.currentTimeMillis();
        System.out.println("[PERF]"+pjp.getSignature().getName()+"took"+(end-start)+"ms");
        return result;
    }
}
