package org.evoting.citizenmanagement.infrastructure.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut für alle Methoden im Service- und Controller-Layer
    @Pointcut("execution(* org.evoting.citizenmanagement.application.services..*(..)) || execution(* org.evoting.citizenmanagement.application.controllers..*(..))")
    public void applicationMethods() {}

    // Vorher-Logging: vor dem Ausführen einer Methode
    @Before("applicationMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    // Nachher-Logging: nach erfolgreichem Abschluss einer Methode
    @AfterReturning(pointcut = "applicationMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} executed successfully, returned: {}", joinPoint.getSignature(), result);
    }

    // Exception-Logging: wenn eine Exception auftritt
    @AfterThrowing(pointcut = "applicationMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("Method {} threw exception: {}", joinPoint.getSignature(), ex.getMessage(), ex);
    }
}



