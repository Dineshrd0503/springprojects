package com.dinesh.springaopdemo.aspect;


import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {
    @AfterThrowing(pointcut = "execution(* com.dinesh.springaopdemo.service.BankService.*(..))",throwing = "ex")
    public void afterThrowingException(Throwable ex){
        System.out.println("[EXC] Exception caught: " + ex.getMessage());
    }

}
