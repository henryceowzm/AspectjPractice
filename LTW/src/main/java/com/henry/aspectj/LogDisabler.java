package com.henry.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogDisabler {
    /*pointcut newPointCut(com.henry.Point p):target(p) && execution(*.new(..));

    after(com.henry.Point p):newPointCut(p)&&!within(LogDisabler){

        System.out.printf("hacked by henry for newPointCut -> %s %n", thisJoinPoint);
        System.out.printf("hacked by henry for newPointCut -> %s %n", p);
    }

    pointcut loggerPointCut(): within(com.henry.App) && call(* org.slf4j.Logger.*(..));

    Object around():loggerPointCut()&&!within(LogDisabler){
        System.out.printf("hacked by henry for loggerPointCut -> %s %n", thisJoinPoint);
        return "true";
    }

    pointcut disableLoggerPointCut(): within(org.jacorb.orb.giop.GIOPConnection) && call(* org.slf4j.Logger.*(..));

    Object around():disableLoggerPointCut()&&!within(LogDisabler){
        System.out.printf("hacked by henry for disableLoggerPointCut -> %s %n", thisJoinPoint);
        return "true";
    }*/

    private static final Logger LOGGER = LoggerFactory.getLogger(LogDisabler.class);
    private static final Boolean AROUND_RETURN_TRUE = Boolean.TRUE;
    private static final Boolean AROUND_RETURN_FALSE = Boolean.FALSE;

    @Pointcut("target(p) && execution(*.new(..))")
    public void newPointCut(com.henry.Point p) {

    }

    @After("newPointCut(p)&&!within(LogDisabler)")
    public void newPointCutAfter(com.henry.Point p, JoinPoint joinPoint) {
//        System.out.printf("hacked by henry for newPointCut -> %s %n", joinPoint);
//        System.out.printf("hacked by henry for newPointCut -> %s %n", p);

        LOGGER.info("hacked by henry for newPointCut -> {}", joinPoint);
        LOGGER.info("hacked by henry for newPointCut -> {}", p);
    }

    @Pointcut("within(com.henry.App) && call(* org.slf4j.Logger.*(..))")
    public void loggerPointCut() {

    }

    @Around("loggerPointCut()&&!within(LogDisabler)")
    public Object loggerPointCutAround(ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable{
//        System.out.printf("hacked by henry for loggerPointCut -> %s %n", joinPoint);
        LOGGER.info("hacked by henry for loggerPointCut -> {}", joinPoint);
//        proceedingJoinPoint.proceed();
        return AROUND_RETURN_TRUE;
    }

    @Pointcut("within(org.jacorb.orb.giop.GIOPConnection) && call(* org.slf4j.Logger.*(..))")
    public void disableLoggerPointCut() {

    }

    @Around("disableLoggerPointCut()&&!within(LogDisabler)")
    public Object disableLoggerPointCutAround(ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) {
//        System.out.printf("hacked by henry for disableLoggerPointCut -> %s %n", joinPoint);
        LOGGER.info("hacked by henry for disableLoggerPointCut -> {}", joinPoint);
        return AROUND_RETURN_TRUE;
    }

}
