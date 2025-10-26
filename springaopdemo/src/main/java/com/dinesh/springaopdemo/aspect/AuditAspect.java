package com.dinesh.springaopdemo.aspect;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditAspect {
    @After("execution(* com.dinesh.springaopdemo.service.BankService.deposit(..)) || execution(* com.dinesh.springaopdemo.service.BankService.withdraw(..))")
    public void auditTX() {
        System.out.println("Transcation has been done");
    }
}
