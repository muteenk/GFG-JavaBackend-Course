package com.schoolproject.management.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value="execution(* com.schoolproject.management.services.impl..*(..))")
    public Object logBefore(ProceedingJoinPoint joinPoint) {
        logger.error(joinPoint.getSignature().getName());
        Object response = new Object();
        try {
            response = joinPoint.proceed();
        } catch (Throwable ex){
            ex.printStackTrace();
        }
        logger.info("Closing the function");

        return response;
    }

    /*
    *
    *   @before
    *
    *   @after
    *
    *   @around
    *
    *   aspect()
    *       -> function()
    *
    *   function()
    *   aspect()
    *
    *
    *
    * */
}
