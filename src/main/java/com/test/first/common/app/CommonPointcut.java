package com.test.first.common.app;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointcut {
	// 포인트컷 설정 메소드들만 따로 모아서 작성한 클래스

	@Pointcut("execution(* com.test.first..*Impl.*(..))")
	public void serviceAllPointcut() {
	}

	// 포인트컷 설정 메소드들만 따로 모아서 작성한 클래스임

	// 포인트컷 설정
	// execution(접근제어자 패키지명.타겟클래스명.타겟메소드명(파라미터))
	// 패키지.. : 모든 하위 패키지를 의미함
	// 매소드(..) : 모든 형식의 매개변수를 의미함

	@Pointcut("execution(* com.test.first..*Impl.select*(..))")
	public void getPointcut() {
	}

	@Pointcut("execution(* com.test.first..*Impl.insert*(..))")
	public void setPointcut() {
	}
}
