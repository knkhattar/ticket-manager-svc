package com.kkcom.tm.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;


public class Logging {
	
	
	
	public void beforeAdvice(JoinPoint joinPoint){
		final Logger logger = Logger.getLogger(joinPoint.getClass());
		Object[] signatureArgs = joinPoint.getArgs();
		logger.info("signature :: "+ joinPoint.getSignature());
		  for (Object signatureArg: signatureArgs) {
		     logger.info("Param : " + signatureArg);
		  }
	}

	public void afterReturningAdvice(JoinPoint joinPoint, Object result ){
		final Logger logger = Logger.getLogger(joinPoint.getClass());
		logger.info("result::"+result);
		
		
	}

}
