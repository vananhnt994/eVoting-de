package org.evoting.citizenmanagement.infrastructure.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitoring {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitoring.class);

    // Pointcut für Methoden im Service- und Controller-Layer
    @Around("execution(* org.evoting.citizenmanagement.application.services..*(..)) || execution(* org.evoting.citizenmanagement.application.controllers..*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            // Methode ausführen
            result = joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            logger.info("Method {} executed in {} ms", joinPoint.getSignature(), executionTime);
        }
        return result;
    }
}

