package com.test.first.common.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service //서비스 클래스에 적용하는
@Aspect //aop　를 의미함
public class BeforeAdvice {
	//목적 : 로그 출력용 메소드를 사용해서 aop를 구동 테스트해 봄
	//어드바이스 클래스 안에서 포인트컷 설정을 직접 작성하고,
	//바로 위빙할 메소드에 포인트컷을 바로 적용 테스트해 봄
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	//메소드 안에 내용이 없어야함
	@Pointcut("execution(* com.test.first..*.*(..))")
	public void allPointcut() {
	}
	
	// 실행시킬 내용에 대한 코드 작성
	// 작동되기 직전
	@Before("allPointcut()")
	public void beforeLog(JoinPoint jp) {
		//포인트컷으로 지정한 타겟오브젝트의 메소드가 실행되기 전에
		//공통으로 작동될 기능에 대한 메소드임
		String methodName = jp.getSignature().getName();
		//타겟오브젝트의  실행할 메소드 이름 추출
		
		Object[] args = jp.getArgs(); // 타겟 메소드의 전달인자 추출
		
		
		logger.info("[메소드 실행전 확인] : " + methodName +"() 메소드 매개변수 갯수 ㅣ "+ args.length);
		
		for(int i =0; i< args.length; i++) {
			if(args[i] != null) {
				logger.info(i + "번째 전달인자 정보" + args[i].toString());
			}
		}
	}
	
	
	
}
