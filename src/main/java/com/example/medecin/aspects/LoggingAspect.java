package com.example.medecin.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspect {
    @After("execution(* com.example.medecin.controllers.*.get*(..))")
    public void apres(JoinPoint thisJoinPoint) {
        log.info("Out of the method (After)" + thisJoinPoint.getSignature().getName());
    }
}
