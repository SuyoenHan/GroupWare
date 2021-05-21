package com.t1works.groupware.kdn.aop;

import org.aspectj.lang.annotation.Pointcut;

public class BoardAOPKdn {

	// === Pointcut(주업무)을 설정해야 한다. === // 
		// Pointcut 이란 공통관심사를 필요로 하는 메소드를 말한다. 
		@Pointcut("execution(public * com.t1works..*Controller.requiredLogin_*(..))")
		public void requiredLogin() {}
	
	
	
	
}
