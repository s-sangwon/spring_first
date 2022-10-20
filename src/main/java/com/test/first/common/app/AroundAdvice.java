package com.test.first.common.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
	//타겟 오브젝트의 메소드가 구동되는 소요 시간을 로그로 출력하는 목적
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Pointcut("execution(* com.test.first..*Impl.*(..))")
	public void servicePointcut(){}
	
	// 타겟 오브젝트의 메소드가 실행되기 전(before)부터
	// 실행된 후(after)까지의 처리를 하는 어드바이스
	@Around("servicePointcut()")
	public Object aroundLog(ProceedingJoinPoint pp) throws Throwable {
		String methodName = pp.getSignature().getName();
		
		//메소드 수행 시간을 체크할 수 있음
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		//타겟메소드가 구동되는 동안 기다림
		Object obj = pp.proceed();
		
		//타겟메소드가 리턴될 때 스톱워치 멈추게 함
		
		stopWatch.stop();
		logger.info(methodName + "() 메소드 수행에 걸린 시간 : "+ stopWatch.getTotalTimeMillis()+ "(ms)초");
		
		return obj;
		
	}
}
