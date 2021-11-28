package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	//set up logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	//setup point cut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")// match any class, any method, any number of args
	public void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")// match any class, any method, any number of args
	public void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")// match any class, any method, any number of args
	public void forDAOPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	public void forAppFlow() {}
	
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		//display method we are calling
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("===> in @Before: calling method: "+method);
		
		//dispaly the arg to the method
		
		//get the arg
		Object[] args = theJoinPoint.getArgs(); 
		
		//loop theu and display arg
		for(Object temp : args) {
			myLogger.info("===> argument: "+temp);
		}
	}
	
	
	//add @AfterReturning advice
	@AfterReturning(pointcut="forAppFlow()", returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		//display method we are calling
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("===> in @AfterReturning: calling method: "+method);
		
		myLogger.info("===> result: "+theResult);
	}
}
