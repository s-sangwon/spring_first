package com.test.first.common.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service // 서비스 클래스에 적용
@Aspect // aop 를 의미
public class AfterAdvice {
	// 목적 : Impl 로 끝나는 클래스들의 모든 메소드가 실행이 완료된
	// 다음에 작동되는 어드바이스 테스트

	private Logger logger = LoggerFactory.getLogger(getClass());

	// 포인트컷 설정에 사용되는 메소드 작성 : 내용없이 작성해야 함
	@Pointcut("execution(* com.test.first..*Impl.*(..))")
	public void implPointcut() {
	}

	// 포인트컷이 설정한 위치에 위빙(들어가서 실행될) 어드바이스 코드 작성
	@After("implPointcut()")
	public void finallyLog(JoinPoint jp) {
		//예외발생 상관없이 무조건 실행되는 어드바이스임
		logger.info("Impl 클래스 메소드 실행 후 작동 : "
				+	"서비스 로직 수행이 완료된 후 무조건 작동됨");	
	
	}
}
