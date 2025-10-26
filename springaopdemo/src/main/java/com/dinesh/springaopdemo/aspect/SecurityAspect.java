package com.dinesh.springaopdemo.aspect;


import com.dinesh.springaopdemo.annotation.Secured;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspect {
    @Before("@annotation(Secured)")
    public void securityCheck(JoinPoint joinPoint,Secured secured){
        System.out.println("[SEC] checkign permission for "+joinPoint.getSignature().getName());
    }

}
